package com.yellowsunn.youtubetrending.domain.youtube

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class YoutubeVideo(
    // pk
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val videoId: String,
    val title: String,
    val channelTitle: String,
    val channelId: String,
    val channelHandle: String?,
    val channelThumbnail: String,
    val description: String,
    val viewCount: Long,
    val publishedTimeText: String,
    val lengthText: String,
    val thumbnail: String,
    val richThumbnail: String?,

    // batch id
    val youtubeTrendingBatchId: Long,

    // video type
    @Enumerated(EnumType.STRING)
    val videoType: YoutubeTrendingVideoType
) {
    fun viewCountText(): String {
        if (viewCount < 1_000L) {
            return "조회수 ${viewCount}회"
        }

        if (viewCount < 10_000L) {
            return String.format("조회수 %.1f천회", viewCount / 1000.0)
        }

        if (viewCount < 100_000L) {
            return String.format("조회수 %.1f만회", viewCount / 10_000.0)
        }

        if (viewCount < 100_000_000L) {
            return "조회수 ${viewCount / 10_000}만회"
        }

        return String.format("조회수 %.1f억회", viewCount / 100_000_000.0)
    }

    fun channelPath(): String {
        if (channelHandle.isNullOrBlank()) {
            return "channel/$channelId"
        }
        return channelHandle
    }
}
