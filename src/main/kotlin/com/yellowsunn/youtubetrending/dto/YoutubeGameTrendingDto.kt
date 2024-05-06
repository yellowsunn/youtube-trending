package com.yellowsunn.youtubetrending.dto

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideo

data class YoutubeGameTrendingDto(
    val videos: List<YoutubeVideo>,
)
