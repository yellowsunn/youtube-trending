package com.yellowsunn.youtubetrending.domain.youtube

import com.yellowsunn.youtubetrending.dto.YoutubeGameTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeMovieTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeMusicTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto

interface YoutubeTrendingHttpClient {
    fun findNowTrending(): YoutubeNowTrendingDto

    fun findMusicTrending(): YoutubeMusicTrendingDto

    fun findGameTrending(): YoutubeGameTrendingDto

    fun findMovieTrending(): YoutubeMovieTrendingDto
}
