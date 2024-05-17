package com.yellowsunn.youtubetrending.domain.youtube

import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto

interface YoutubeTrendingHttpClient {
    fun findNowTrending(): YoutubeNowTrendingDto
//
//    fun findOtherTrending(trendingType: YoutubeOtherTrendingType): YoutubeOtherTrendingDto
}
