package com.yellowsunn.youtubetrending.dto

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideo

data class YoutubeMovieTrendingDto(
    val videos: List<YoutubeVideo>,
)
