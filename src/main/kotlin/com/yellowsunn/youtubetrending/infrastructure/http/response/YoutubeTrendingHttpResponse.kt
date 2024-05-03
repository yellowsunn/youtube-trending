package com.yellowsunn.youtubetrending.infrastructure.http.response

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeShort
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideo

data class YoutubeTrendingHttpResponse(
    val data: List<YoutubeData>,
) {
    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true,
    )
    @JsonSubTypes(
        JsonSubTypes.Type(value = YoutubeVideoData::class, name = "video"),
        JsonSubTypes.Type(value = YoutubeShortListingData::class, name = "shorts_listing"),
        JsonSubTypes.Type(value = YoutubeVideoListingData::class, name = "video_listing"),
    )
    interface YoutubeData

    data class YoutubeVideoData(
        val type: String,
        val videoId: String,
        val title: String,
        val channelTitle: String,
        val channelId: String,
        val channelHandle: String?,
        val channelThumbnail: List<Thumbnail>,
        val description: String,
        val viewCount: Long,
        val publishedTimeText: String,
        val lengthText: String,
        val thumbnail: List<Thumbnail>,
    ) : YoutubeData {
        fun toYoutubeVideo(): YoutubeVideo {
            return YoutubeVideo(
                videoId = videoId,
                title = title,
                channelTitle = channelTitle,
                channelId = channelId,
                channelHandle = channelHandle ?: "",
                channelThumbnail = channelThumbnail.lastOrNull()?.url ?: "",
                description = description,
                viewCount = viewCount,
                publishedTimeText = publishedTimeText,
                lengthText = lengthText,
                thumbnail = thumbnail.lastOrNull()?.url ?: "",
            )
        }
    }

    data class YoutubeShortListingData(
        val type: String,
        val title: String,
        val data: List<ShortData>,
    ) : YoutubeData {
        data class ShortData(
            val type: String,
            val videoId: String,
            val title: String,
            val viewCountText: String,
            val thumbnail: List<Thumbnail>,
            val isOriginalAspectRatio: Boolean,
            val params: String,
            val playerParams: String,
            val sequenceParams: String,
        )

        fun toYoutubeShorts(): List<YoutubeShort> {
            return this.data.map {
                YoutubeShort(
                    videoId = it.videoId,
                    title = it.title,
                    viewCountText = it.viewCountText,
                    thumbnail = it.thumbnail.lastOrNull()?.url ?: "",
                )
            }
        }
    }

    data class YoutubeVideoListingData(
        val type: String,
        val title: String,
        val data: List<YoutubeListData>,
    ) : YoutubeData {
        data class YoutubeListData(
            val type: String,
            val videoId: String,
            val title: String,
            val channelTitle: String?,
            val channelId: String?,
            val channelHandle: String?,
            val channelThumbnail: List<Thumbnail>?,
            val description: String?,
            val viewCount: Long,
            val publishedTimeText: String,
            val lengthText: String,
            val thumbnail: List<Thumbnail>,
        ) {
            fun toYoutubeVideo(): YoutubeVideo {
                return YoutubeVideo(
                    videoId = videoId,
                    title = title,
                    channelTitle = channelTitle ?: "",
                    channelId = channelId ?: "",
                    channelHandle = channelHandle ?: "",
                    channelThumbnail = channelThumbnail?.lastOrNull()?.url ?: "",
                    description = description ?: "",
                    viewCount = viewCount,
                    publishedTimeText = publishedTimeText,
                    lengthText = lengthText,
                    thumbnail = thumbnail.lastOrNull()?.url ?: "",
                )
            }
        }
    }

    data class Thumbnail(
        val url: String,
        val width: Int,
        val height: Int,
    )

    fun toTrendingVideos(): List<YoutubeVideo> {
        return this.data
            .filterIsInstance<YoutubeVideoData>()
            .map { it.toYoutubeVideo() }
    }

    fun toTrendingShorts(): List<YoutubeShort> {
        return this.data
            .filterIsInstance<YoutubeShortListingData>()
            .flatMap { it.toYoutubeShorts() }
    }

    fun toRecentlyTrendingVideos(): List<YoutubeVideo> {
        val recentlyTrending: YoutubeVideoListingData? =
            this.data
                .filterIsInstance<YoutubeVideoListingData>()
                .lastOrNull()

        if (recentlyTrending == null) {
            return emptyList()
        }

        return recentlyTrending.data
            .map { it.toYoutubeVideo() }
    }
}
