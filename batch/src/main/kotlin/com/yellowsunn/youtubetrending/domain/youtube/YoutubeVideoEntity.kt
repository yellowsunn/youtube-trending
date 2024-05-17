package com.yellowsunn.youtubetrending.domain.youtube

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class YoutubeVideoEntity(
    // pk
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
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
    val videoType: String,
)
