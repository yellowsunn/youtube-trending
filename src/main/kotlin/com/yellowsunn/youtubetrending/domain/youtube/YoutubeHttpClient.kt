package com.yellowsunn.youtubetrending.domain.youtube

import com.yellowsunn.youtubetrending.dto.YoutubeTrendingAllDto

interface YoutubeHttpClient {
    fun findTrendingAll(): YoutubeTrendingAllDto
}
