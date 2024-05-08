package com.yellowsunn.youtubetrending.infrastructure.mock

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeTrendingHttpResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource

class JsonFileConvertTest {
    @Test
    fun `json 파일에서 객체 변환하기`() {
        val resource = ClassPathResource("json/youtube-now-trending-sample.json")
        val inputStream = resource.inputStream
        val objectMapper: ObjectMapper =
            jacksonObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .registerModule(JavaTimeModule())

        val response: YoutubeTrendingHttpResponse = objectMapper.readValue<YoutubeTrendingHttpResponse>(inputStream)

        assertThat(response.data).hasSize(51)
    }
}
