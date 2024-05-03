package com.yellowsunn.youtubetrending.infrastructure.mock

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeHttpClient
import com.yellowsunn.youtubetrending.dto.YoutubeTrendingAllDto
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeTrendingHttpResponse
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class YoutubeMockHttpClient : YoutubeHttpClient {
    private val objectMapper =
        jacksonObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerModule(JavaTimeModule())

    override fun findTrendingAll(): YoutubeTrendingAllDto {
        val resource = ClassPathResource("json/youtube-trending-sample.json")

        val mockHttpResponse: YoutubeTrendingHttpResponse =
            objectMapper.readValue<YoutubeTrendingHttpResponse>(resource.inputStream)

        return YoutubeTrendingAllDto(
            videos = mockHttpResponse.toTrendingVideos(),
            shorts = mockHttpResponse.toTrendingShorts(),
            recentlyVideos = mockHttpResponse.toRecentlyTrendingVideos(),
        )
    }
}
