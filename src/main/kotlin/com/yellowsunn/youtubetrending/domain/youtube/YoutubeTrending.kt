package com.yellowsunn.youtubetrending.domain.youtube

import java.time.ZonedDateTime

data class YoutubeTrending(
    val videoId: String,
    val publishedAt: ZonedDateTime,
    val channelId: String,
    val title: String,
    val description: String,
    val channelTitle: String,
    val thumbnail: String,
)
