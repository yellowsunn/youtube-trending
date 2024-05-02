package com.yellowsunn.youtubetrending.domain.youtube

data class YoutubeVideo(
    val videoId: String,
    val title: String,
    val channelTitle: String,
    val channelId: String,
    val channelHandle: String,
    val channelThumbnail: String,
    val description: String,
    val viewCount: Long,
    val publishedTimeText: String,
    val lengthText: String,
    val thumbnail: String,
)
