package com.yellowsunn.youtubetrending.infrastructure.http

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeHttpClient
import com.yellowsunn.youtubetrending.dto.YoutubeTrendingAllDto

class YoutubeFeignClient : YoutubeHttpClient {
    override fun findTrendingAll(): YoutubeTrendingAllDto {
        TODO("Not yet implemented")
    }
}
