package com.yellowsunn.youtubetrending.application

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.dto.YoutubeGameTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeMovieTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeMusicTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import org.springframework.stereotype.Service

@Service
class YoutubeTrendingService(
    private val youtubeTrendingHttpClient: YoutubeTrendingHttpClient,
) {
    fun findNow(): YoutubeNowTrendingDto {
        return youtubeTrendingHttpClient.findNowTrending()
    }

    fun findMusic(): YoutubeMusicTrendingDto {
        return youtubeTrendingHttpClient.findMusicTrending()
    }

    fun findGame(): YoutubeGameTrendingDto {
        return youtubeTrendingHttpClient.findGameTrending()
    }

    fun findMovie(): YoutubeMovieTrendingDto {
        return youtubeTrendingHttpClient.findMovieTrending()
    }
}
