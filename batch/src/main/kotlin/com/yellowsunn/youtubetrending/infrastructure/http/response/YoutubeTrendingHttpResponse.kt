package com.yellowsunn.youtubetrending.infrastructure.http.response

import com.yellowsunn.youtubetrending.domain.youtube.response.YoutubeShort
import com.yellowsunn.youtubetrending.domain.youtube.response.YoutubeVideo

data class YoutubeTrendingHttpResponse(
    val data: List<YoutubeTrendingData>,
) {
    fun toTrendingVideos(): List<YoutubeVideo> {
        return this.data
            .filterIsInstance<YoutubeVideoData>()
            .map { it.toYoutubeVideo() }
    }

    fun toTrendingShorts(): List<YoutubeShort> {
        return this.data
            .filterIsInstance<YoutubeShortListingData>()
            .flatMap { it.toYoutubeShorts() }
    }

    fun toRecentlyTrendingVideos(): List<YoutubeVideo> {
        val recentlyTrending: YoutubeVideoListingData? =
            this.data
                .filterIsInstance<YoutubeVideoListingData>()
                .lastOrNull()

        if (recentlyTrending == null) {
            return emptyList()
        }

        return recentlyTrending.data
            .map { it.toYoutubeVideo() }
    }
}
