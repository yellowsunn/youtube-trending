package com.yellowsunn.youtubetrending.infrastructure.mock

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.dto.YoutubeMusicTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeMusicTrendingHttpResponse
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeNowTrendingHttpResponse
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

//@Component
class YoutubeMockTrendingHttpClient : YoutubeTrendingHttpClient {
    private val objectMapper =
        jacksonObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerModule(JavaTimeModule())

    override fun findNowTrending(): YoutubeNowTrendingDto {
        val resource = ClassPathResource("json/youtube-now-trending-sample.json")

        val mockHttpResponse: YoutubeNowTrendingHttpResponse =
            objectMapper.readValue<YoutubeNowTrendingHttpResponse>(resource.inputStream)

        return YoutubeNowTrendingDto(
            videos = mockHttpResponse.toTrendingVideos(),
            shorts = mockHttpResponse.toTrendingShorts(),
            recentlyVideos = mockHttpResponse.toRecentlyTrendingVideos(),
        )
    }

    override fun findMusicTrending(): YoutubeMusicTrendingDto {
        val resource = ClassPathResource("json/youtube-music-trending-sample.json")

        val mockHttpResponse = objectMapper.readValue<YoutubeMusicTrendingHttpResponse>(resource.inputStream)

        return YoutubeMusicTrendingDto(
            videos = mockHttpResponse.toTrendingVideos(),
        )
    }
}
