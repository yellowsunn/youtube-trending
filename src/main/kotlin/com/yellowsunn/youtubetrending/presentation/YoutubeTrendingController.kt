package com.yellowsunn.youtubetrending.presentation

import com.yellowsunn.youtubetrending.application.YoutubeTrendingService
import com.yellowsunn.youtubetrending.dto.YoutubeTrendingAllDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class YoutubeTrendingController(
    private val youtubeTrendingService: YoutubeTrendingService,
) {
    @GetMapping("/")
    fun index(model: Model): String {
        val trendingVideos: YoutubeTrendingAllDto = youtubeTrendingService.findAll()

        return "youtube-trending"
    }
}
