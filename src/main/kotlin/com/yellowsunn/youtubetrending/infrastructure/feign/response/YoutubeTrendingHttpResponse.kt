package com.yellowsunn.youtubetrending.infrastructure.feign.response

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrending
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime

data class YoutubeTrendingHttpResponse(
    val pageInfo: PageInfo,
    val items: List<Item>,
) {
    data class PageInfo(
        val totalResults: Long,
        val resultsPerPage: Int,
    )

    data class Item(
        val id: String,
        val snippet: Snippet,
    ) {
        data class Snippet(
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
            val publishedAt: ZonedDateTime,
            val channelId: String,
            val title: String,
            val description: String,
            val thumbnails: Thumbnail,
            val channelTitle: String,
        ) {
            data class Thumbnail(
                val high: High,
            ) {
                data class High(
                    val url: String,
                    val width: Int,
                    val height: Int,
                )
            }
        }
    }

    fun toYoutubeTrending(): List<YoutubeTrending> {
        return this.items.map {
            YoutubeTrending(
                videoId = it.id,
                publishedAt = it.snippet.publishedAt,
                channelId = it.snippet.channelId,
                title = it.snippet.title,
                description = it.snippet.description,
                channelTitle = it.snippet.channelTitle,
                thumbnail = it.snippet.thumbnails.high.url,
            )
        }
    }
}
