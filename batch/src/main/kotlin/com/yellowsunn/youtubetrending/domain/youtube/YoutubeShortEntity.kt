package com.yellowsunn.youtubetrending.domain.youtube

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class YoutubeShortEntity(
    // pk
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val videoId: String,
    val title: String,
    val viewCountText: String,
    val thumbnail: String,
    // batch id
    val youtubeTrendingBatchId: Long,
)
