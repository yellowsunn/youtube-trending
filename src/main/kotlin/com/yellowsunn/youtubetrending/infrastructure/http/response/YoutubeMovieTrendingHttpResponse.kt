package com.yellowsunn.youtubetrending.infrastructure.http.response

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideo

data class YoutubeMovieTrendingHttpResponse(
    val data: List<YoutubeVideoData>,
) {
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
        val richThumbnail: List<Thumbnail>?,
    ) {
        data class Thumbnail(
            val url: String,
            val width: Int,
            val height: Int,
        )

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
            )
        }
    }

    fun toTrendingVideos(): List<YoutubeVideo> {
        return this.data
            .map { it.toYoutubeVideo() }
    }
}
