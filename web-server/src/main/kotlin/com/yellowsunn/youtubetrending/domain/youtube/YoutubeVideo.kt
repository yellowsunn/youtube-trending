package com.yellowsunn.youtubetrending.domain.youtube

data class YoutubeVideo(
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
