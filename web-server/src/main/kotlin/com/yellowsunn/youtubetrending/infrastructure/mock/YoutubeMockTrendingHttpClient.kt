package com.yellowsunn.youtubetrending.infrastructure.mock

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeOtherTrendingType
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeOtherTrendingDto
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeTrendingHttpResponse
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
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

    override fun findOtherTrending(trendingType: YoutubeOtherTrendingType): YoutubeOtherTrendingDto {
        val resource =
            when (trendingType) {
                YoutubeOtherTrendingType.MUSIC -> ClassPathResource("json/youtube-music-trending-sample.json")
                YoutubeOtherTrendingType.GAMES -> ClassPathResource("json/youtube-game-trending-sample.json")
                YoutubeOtherTrendingType.MOVIES -> ClassPathResource("json/youtube-movie-trending-sample.json")
            }

        val mockHttpResponse = objectMapper.readValue<YoutubeTrendingHttpResponse>(resource.inputStream)

        return YoutubeOtherTrendingDto(
            videos = mockHttpResponse.toTrendingVideos(),
        )
    }
}
