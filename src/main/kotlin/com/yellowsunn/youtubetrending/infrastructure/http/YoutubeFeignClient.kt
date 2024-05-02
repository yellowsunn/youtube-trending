package com.yellowsunn.youtubetrending.infrastructure.http

import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeTrendingHttpResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "youtube-api")
interface YoutubeFeignClient {
    @GetMapping(
        "/trending",
        headers = ["X-RapidAPI-Host=yt-api.p.rapidapi.com", "X-RapidAPI-Key=\${rapid-api.key}"],
    )
    fun getTrending(
        @RequestParam geo: String,
        @RequestParam lang: String,
    ): YoutubeTrendingHttpResponse
}
