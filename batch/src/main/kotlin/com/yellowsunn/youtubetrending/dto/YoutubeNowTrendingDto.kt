package com.yellowsunn.youtubetrending.dto

import com.yellowsunn.youtubetrending.domain.youtube.response.YoutubeShort
import com.yellowsunn.youtubetrending.domain.youtube.response.YoutubeVideo

data class YoutubeNowTrendingDto(
    val videos: List<YoutubeVideo>,
    val shorts: List<YoutubeShort>,
    val recentlyVideos: List<YoutubeVideo>,
)
