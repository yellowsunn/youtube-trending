package com.yellowsunn.youtubetrending.domain.port

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrending

interface YoutubePort {
    fun findTrendingVideos(): List<YoutubeTrending>
}
