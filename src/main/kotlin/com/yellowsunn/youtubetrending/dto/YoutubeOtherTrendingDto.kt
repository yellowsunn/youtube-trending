package com.yellowsunn.youtubetrending.dto

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideo

data class YoutubeOtherTrendingDto(
    val videos: List<YoutubeVideo>,
)
