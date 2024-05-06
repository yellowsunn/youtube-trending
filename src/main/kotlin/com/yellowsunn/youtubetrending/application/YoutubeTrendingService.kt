package com.yellowsunn.youtubetrending.application

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingType
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeOtherTrendingDto
import org.springframework.stereotype.Service

@Service
class YoutubeTrendingService(
    private val youtubeTrendingHttpClient: YoutubeTrendingHttpClient,
) {
    fun findNow(): YoutubeNowTrendingDto {
        return youtubeTrendingHttpClient.findNowTrending()
    }

    fun findMusic(): YoutubeOtherTrendingDto {
        return youtubeTrendingHttpClient.findOtherTrending(YoutubeTrendingType.MUSIC)
    }

    fun findGame(): YoutubeOtherTrendingDto {
        return youtubeTrendingHttpClient.findOtherTrending(YoutubeTrendingType.GAMES)
    }

    fun findMovie(): YoutubeOtherTrendingDto {
        return youtubeTrendingHttpClient.findOtherTrending(YoutubeTrendingType.MOVIES)
    }
}
