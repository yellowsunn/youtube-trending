package com.yellowsunn.youtubetrending.infrastructure.http

import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeGameTrendingHttpResponse
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeMovieTrendingHttpResponse
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeMusicTrendingHttpResponse
import com.yellowsunn.youtubetrending.infrastructure.http.response.YoutubeNowTrendingHttpResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "youtube-api")
interface YoutubeTrendingFeignClient {
    @GetMapping(
        "/trending?type=now&geo=KR&lang=ko",
        headers = ["X-RapidAPI-Host=yt-api.p.rapidapi.com", "X-RapidAPI-Key=\${rapid-api.key}"],
    )
    fun getNowTrending(): YoutubeNowTrendingHttpResponse

    @GetMapping(
        "/trending?type=music&geo=KR&lang=ko",
        headers = ["X-RapidAPI-Host=yt-api.p.rapidapi.com", "X-RapidAPI-Key=\${rapid-api.key}"],
    )
    fun getMusicTrending(): YoutubeMusicTrendingHttpResponse

    @GetMapping(
        "/trending?type=games&geo=KR&lang=ko",
        headers = ["X-RapidAPI-Host=yt-api.p.rapidapi.com", "X-RapidAPI-Key=\${rapid-api.key}"],
    )
    fun getGameTrending(): YoutubeGameTrendingHttpResponse

    @GetMapping(
        "/trending?type=movies&geo=KR&lang=ko",
        headers = ["X-RapidAPI-Host=yt-api.p.rapidapi.com", "X-RapidAPI-Key=\${rapid-api.key}"],
    )
    fun getMovieTrending(): YoutubeMovieTrendingHttpResponse
}
