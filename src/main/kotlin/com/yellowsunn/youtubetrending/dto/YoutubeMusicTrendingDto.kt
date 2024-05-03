package com.yellowsunn.youtubetrending.dto

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideo

data class YoutubeMusicTrendingDto(
    val videos: List<YoutubeVideo>,
)
