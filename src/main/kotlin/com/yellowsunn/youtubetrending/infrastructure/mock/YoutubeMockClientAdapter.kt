package com.yellowsunn.youtubetrending.infrastructure.mock

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.yellowsunn.youtubetrending.domain.port.YoutubePort
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrending
import com.yellowsunn.youtubetrending.infrastructure.feign.response.YoutubeTrendingHttpResponse
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class YoutubeMockClientAdapter : YoutubePort {
    private val objectMapper =
        jacksonObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerModule(JavaTimeModule())

    override fun findTrendingVideos(): List<YoutubeTrending> {
        val resource = ClassPathResource("json/youtube-trending-sample.json")

        val mockHttpResponse: YoutubeTrendingHttpResponse =
            objectMapper.readValue<YoutubeTrendingHttpResponse>(resource.inputStream)

        return mockHttpResponse.toYoutubeTrending()
    }
}
