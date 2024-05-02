package com.yellowsunn.youtubetrending.application

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeHttpClient
import com.yellowsunn.youtubetrending.dto.YoutubeTrendingAllDto
import org.springframework.stereotype.Service

@Service
class YoutubeTrendingService(
    private val youtubeHttpClient: YoutubeHttpClient,
) {
    fun findAll(): YoutubeTrendingAllDto {
        return youtubeHttpClient.findTrendingAll()
    }
}
