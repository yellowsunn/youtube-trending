package com.yellowsunn.youtubetrending.application

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeOtherTrendingType
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
        return youtubeTrendingHttpClient.findOtherTrending(YoutubeOtherTrendingType.MUSIC)
    }

    fun findGame(): YoutubeOtherTrendingDto {
        return youtubeTrendingHttpClient.findOtherTrending(YoutubeOtherTrendingType.GAMES)
    }

    fun findMovie(): YoutubeOtherTrendingDto {
        return youtubeTrendingHttpClient.findOtherTrending(YoutubeOtherTrendingType.MOVIES)
    }
}
