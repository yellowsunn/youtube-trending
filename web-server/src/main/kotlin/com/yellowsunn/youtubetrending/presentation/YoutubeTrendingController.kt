package com.yellowsunn.youtubetrending.presentation

import com.yellowsunn.youtubetrending.application.YoutubeTrendingService
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideo
import com.yellowsunn.youtubetrending.dto.YoutubeNowTrendingDto
import com.yellowsunn.youtubetrending.dto.YoutubeOtherTrendingDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import kotlin.math.min

@Controller
class YoutubeTrendingController(
    private val youtubeTrendingService: YoutubeTrendingService,
) {
    companion object {
        private const val DIVIDING_SIZE = 2
    }

    @GetMapping("/")
    fun index(model: Model): String {
        val trending: YoutubeNowTrendingDto = youtubeTrendingService.findNow()
        model.addAttribute("type", "now")
        model.addAttribute("topVideos", trending.videos.top())
        model.addAttribute("bottomVideos", trending.videos.bottom())
        model.addAttribute("recentlyVideos", trending.recentlyVideos)
        model.addAttribute("shorts", trending.shorts)

        return "youtube-trending"
    }

    @GetMapping("/music")
    fun music(model: Model): String {
        val trending: YoutubeOtherTrendingDto = youtubeTrendingService.findMusic()
        model.addAttribute("type", "music")
        model.addAttribute("videos", trending.videos)

        return "youtube-trending"
    }

    @GetMapping("/game")
    fun game(model: Model): String {
        val trending: YoutubeOtherTrendingDto = youtubeTrendingService.findGame()
        model.addAttribute("type", "game")
        model.addAttribute("videos", trending.videos)

        return "youtube-trending"
    }

    @GetMapping("/movie")
    fun movie(model: Model): String {
        val trending: YoutubeOtherTrendingDto = youtubeTrendingService.findMovie()
        model.addAttribute("type", "movie")
        model.addAttribute("videos", trending.videos)

        return "youtube-trending"
    }

    private fun List<YoutubeVideo>.top(): List<YoutubeVideo> {
        return this.subList(0, min(this.size, DIVIDING_SIZE))
    }

    private fun List<YoutubeVideo>.bottom(): List<YoutubeVideo> {
        return if (this.size < DIVIDING_SIZE) {
            listOf()
        } else {
            this.subList(DIVIDING_SIZE, this.size)
        }
    }
}
