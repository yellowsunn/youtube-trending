package com.yellowsunn.youtubetrending.application

import com.yellowsunn.youtubetrending.domain.port.YoutubePort
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrending
import org.springframework.stereotype.Service

@Service
class YoutubeTrendingService(
    private val youtubePort: YoutubePort,
) {
    fun findAll(): List<YoutubeTrending> {
        return youtubePort.findTrendingVideos()
    }
}
