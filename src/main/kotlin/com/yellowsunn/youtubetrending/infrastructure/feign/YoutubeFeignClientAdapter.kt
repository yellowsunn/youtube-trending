package com.yellowsunn.youtubetrending.infrastructure.feign

import com.yellowsunn.youtubetrending.domain.port.YoutubePort
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrending

class YoutubeFeignClientAdapter : YoutubePort {
    override fun findTrendingVideos(): List<YoutubeTrending> {
        TODO("Not yet implemented")
    }
}
