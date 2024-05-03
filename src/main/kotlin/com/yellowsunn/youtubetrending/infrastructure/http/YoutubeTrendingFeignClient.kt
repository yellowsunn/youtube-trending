package com.yellowsunn.youtubetrending.infrastructure.http

import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeMusicTrendingHttpResponse
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeNowTrendingHttpResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "youtube-api")
interface YoutubeTrendingFeignClient {
    @GetMapping(
        "/trending",
        headers = ["X-RapidAPI-Host=yt-api.p.rapidapi.com", "X-RapidAPI-Key=\${rapid-api.key}"],
        params = ["type=now", "geo=KR", "lang=ko"],
    )
    fun getNowTrending(): YoutubeNowTrendingHttpResponse

    @GetMapping(
        "/trending",
        headers = ["X-RapidAPI-Host=yt-api.p.rapidapi.com", "X-RapidAPI-Key=\${rapid-api.key}"],
        params = ["type=music", "geo=KR", "lang=ko"],
    )
    fun getMusicTrending(): YoutubeMusicTrendingHttpResponse
}
