package com.yellowsunn.youtubetrending.domain.youtube.response

data class YoutubeShort(
    val videoId: String,
    val title: String,
    val viewCountText: String,
    val thumbnail: String,
)
