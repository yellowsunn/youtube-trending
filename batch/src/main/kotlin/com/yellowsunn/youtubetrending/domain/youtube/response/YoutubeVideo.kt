package com.yellowsunn.youtubetrending.domain.youtube.response

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingVideoType
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideoEntity

data class YoutubeVideo(
    val videoId: String,
    val title: String,
    val channelTitle: String,
    val channelId: String,
    val channelHandle: String?,
    val channelThumbnail: String,
    val description: String,
    val viewCount: Long,
    val publishedTimeText: String,
    val lengthText: String,
    val thumbnail: String,
    val richThumbnail: String?,
) {
    fun toEntity(
        batchHistoryId: Long,
        videoType: YoutubeTrendingVideoType = YoutubeTrendingVideoType.MUSIC,
    ): YoutubeVideoEntity {
        return YoutubeVideoEntity(
            videoId = this.videoId,
            title = this.title,
            channelTitle = this.channelTitle,
            channelId = this.channelId,
            channelHandle = this.channelHandle,
            channelThumbnail = this.channelThumbnail,
            description = this.description,
            viewCount = this.viewCount,
            publishedTimeText = this.publishedTimeText,
            lengthText = this.lengthText,
            thumbnail = this.thumbnail,
            richThumbnail = this.richThumbnail,
            youtubeTrendingBatchId = batchHistoryId,
            videoType = videoType.name,
        )
    }
}
