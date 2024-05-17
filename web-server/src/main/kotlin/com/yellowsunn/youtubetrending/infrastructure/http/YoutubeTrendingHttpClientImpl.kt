package com.yellowsunn.youtubetrending.infrastructure.http

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeOtherTrendingType
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeOtherTrendingDto
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeTrendingHttpResponse
import org.springframework.stereotype.Component

//@Component
class YoutubeTrendingHttpClientImpl(
    private val youtubeTrendingFeignClient: YoutubeTrendingFeignClient,
) : YoutubeTrendingHttpClient {
    override fun findNowTrending(): YoutubeNowTrendingDto {
        val trending: YoutubeTrendingHttpResponse = youtubeTrendingFeignClient.getTrending("now")

        return YoutubeNowTrendingDto(
            videos = trending.toTrendingVideos(),
            shorts = trending.toTrendingShorts(),
            recentlyVideos = trending.toRecentlyTrendingVideos(),
        )
    }

    override fun findOtherTrending(trendingType: YoutubeOtherTrendingType): YoutubeOtherTrendingDto {
        val trending: YoutubeTrendingHttpResponse = youtubeTrendingFeignClient.getTrending(trendingType.type)

        return YoutubeOtherTrendingDto(
            videos = trending.toTrendingVideos(),
        )
    }
}
