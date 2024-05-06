package com.yellowsunn.youtubetrending.infrastructure.http

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.dto.YoutubeMovieTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeMusicTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeMusicTrendingHttpResponse
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeNowTrendingHttpResponse
import org.springframework.stereotype.Component

//@Component
class YoutubeTrendingHttpClientImpl(
    private val youtubeTrendingFeignClient: YoutubeTrendingFeignClient,
) : YoutubeTrendingHttpClient {
    override fun findNowTrending(): YoutubeNowTrendingDto {
        val trending: YoutubeNowTrendingHttpResponse = youtubeTrendingFeignClient.getNowTrending()

        return YoutubeNowTrendingDto(
            videos = trending.toTrendingVideos(),
            shorts = trending.toTrendingShorts(),
            recentlyVideos = trending.toRecentlyTrendingVideos(),
        )
    }

    override fun findMusicTrending(): YoutubeMusicTrendingDto {
        val trending: YoutubeMusicTrendingHttpResponse = youtubeTrendingFeignClient.getMusicTrending()

        return YoutubeMusicTrendingDto(
            videos = trending.toTrendingVideos(),
        )
    }

    override fun findMovieTrending(): YoutubeMovieTrendingDto {
        TODO("Not yet implemented")
    }
}
