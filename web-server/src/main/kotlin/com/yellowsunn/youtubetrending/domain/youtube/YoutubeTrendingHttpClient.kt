package com.yellowsunn.youtubetrending.domain.youtube

import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeOtherTrendingDto

interface YoutubeTrendingHttpClient {
    fun findNowTrending(): YoutubeNowTrendingDto

    fun findOtherTrending(trendingType: YoutubeOtherTrendingType): YoutubeOtherTrendingDto
}
