package com.yellowsunn.youtubetrending.infrastructure.http

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeHttpClient
import com.yellowsunn.youtubetrending.dto.YoutubeTrendingAllDto
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeTrendingHttpResponse
import org.springframework.stereotype.Component

//@Component
class YoutubeHttpClientImpl(
    private val youtubeFeignClient: YoutubeFeignClient,
) : YoutubeHttpClient {
    override fun findTrendingAll(): YoutubeTrendingAllDto {
        val trending: YoutubeTrendingHttpResponse = youtubeFeignClient.getTrending(geo = "KR", lang = "ko")

        return YoutubeTrendingAllDto(
            videos = trending.toTrendingVideos(),
            shorts = trending.toTrendingShorts(),
            recentlyVideos = trending.toRecentlyTrendingVideos(),
        )
    }
}
