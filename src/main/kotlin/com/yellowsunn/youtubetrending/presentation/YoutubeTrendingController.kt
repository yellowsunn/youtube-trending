package com.yellowsunn.youtubetrending.presentation

import com.yellowsunn.youtubetrending.application.YoutubeTrendingService
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrending
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class YoutubeTrendingController(
    private val youtubeTrendingService: YoutubeTrendingService,
) {
    @GetMapping("/")
    fun index(model: Model): String {
        val trendingVideos: List<YoutubeTrending> = youtubeTrendingService.findAll()

        model.addAttribute("trendingVideos", trendingVideos)
        return "youtube-trending"
    }
}
