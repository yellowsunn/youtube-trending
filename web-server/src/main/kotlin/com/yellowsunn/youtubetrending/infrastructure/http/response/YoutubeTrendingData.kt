package com.yellowsunn.youtubetrending.infrastructure.http.response

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeShort
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideo

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
interface YoutubeTrendingData

data class YoutubeVideoData(
    val type: String,
    val videoId: String,
    val title: String,
    val channelTitle: String,
    val channelId: String,
    val channelHandle: String?,
    val channelThumbnail: List<YoutubeThumbnail>,
    val description: String,
    val viewCount: Long,
    val publishedTimeText: String,
    val lengthText: String,
    val thumbnail: List<YoutubeThumbnail>,
    val richThumbnail: List<YoutubeThumbnail>?,
) : YoutubeTrendingData {
    fun toYoutubeVideo(): YoutubeVideo {
        return YoutubeVideo(
            videoId = videoId,
            title = title,
            channelTitle = channelTitle,
            channelId = channelId,
            channelHandle = channelHandle,
            channelThumbnail = channelThumbnail.lastOrNull()?.url ?: "",
            description = description,
            viewCount = viewCount,
            publishedTimeText = publishedTimeText,
            lengthText = lengthText,
            thumbnail = thumbnail.lastOrNull()?.url ?: "",
            richThumbnail = richThumbnail?.lastOrNull()?.url ?: "",
        )
    }
}

data class YoutubeShortListingData(
    val type: String,
    val title: String,
    val data: List<ShortData>,
) : YoutubeTrendingData {
    data class ShortData(
        val type: String,
        val videoId: String,
        val title: String,
        val viewCountText: String,
        val thumbnail: List<YoutubeThumbnail>,
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
) : YoutubeTrendingData {
    data class YoutubeListData(
        val type: String,
        val videoId: String,
        val title: String,
        val channelTitle: String?,
        val channelId: String?,
        val channelHandle: String?,
        val channelThumbnail: List<YoutubeThumbnail>?,
        val description: String?,
        val viewCount: Long,
        val publishedTimeText: String,
        val lengthText: String,
        val thumbnail: List<YoutubeThumbnail>,
        val richThumbnail: List<YoutubeThumbnail>?,
    ) {
        fun toYoutubeVideo(): YoutubeVideo {
            return YoutubeVideo(
                videoId = videoId,
                title = title,
                channelTitle = channelTitle ?: "",
                channelId = channelId ?: "",
                channelHandle = channelHandle,
                channelThumbnail = channelThumbnail?.lastOrNull()?.url ?: "",
                description = description ?: "",
                viewCount = viewCount,
                publishedTimeText = publishedTimeText,
                lengthText = lengthText,
                thumbnail = thumbnail.lastOrNull()?.url ?: "",
                richThumbnail = richThumbnail?.lastOrNull()?.url ?: "",
            )
        }
    }
}

data class YoutubeThumbnail(
    val url: String,
    val width: Int,
    val height: Int,
)
