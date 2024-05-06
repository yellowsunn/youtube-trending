package com.yellowsunn.youtubetrending.infrastructure.mock

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingType
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeOtherTrendingDto
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeTrendingHttpResponse
import org.springframework.core.io.ClassPathResource

// @Component
class YoutubeMockTrendingHttpClient : YoutubeTrendingHttpClient {
    private val objectMapper =
        jacksonObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerModule(JavaTimeModule())

    override fun findNowTrending(): YoutubeNowTrendingDto {
        val resource = ClassPathResource("json/youtube-now-trending-sample.json")

        val mockHttpResponse: YoutubeTrendingHttpResponse =
            objectMapper.readValue<YoutubeTrendingHttpResponse>(resource.inputStream)

        return YoutubeNowTrendingDto(
            videos = mockHttpResponse.toTrendingVideos(),
            shorts = mockHttpResponse.toTrendingShorts(),
            recentlyVideos = mockHttpResponse.toRecentlyTrendingVideos(),
        )
    }

    override fun findOtherTrending(trendingType: YoutubeTrendingType): YoutubeOtherTrendingDto {
        val resource =
            when (trendingType) {
                YoutubeTrendingType.MUSIC -> ClassPathResource("json/youtube-music-trending-sample.json")
                YoutubeTrendingType.GAMES -> ClassPathResource("json/youtube-game-trending-sample.json")
                YoutubeTrendingType.MOVIES -> ClassPathResource("json/youtube-movie-trending-sample.json")
                YoutubeTrendingType.NOW -> throw IllegalArgumentException("유효한 타입이 아닙니다.")
            }

        val mockHttpResponse = objectMapper.readValue<YoutubeTrendingHttpResponse>(resource.inputStream)

        return YoutubeOtherTrendingDto(
            videos = mockHttpResponse.toTrendingVideos(),
        )
    }
}
