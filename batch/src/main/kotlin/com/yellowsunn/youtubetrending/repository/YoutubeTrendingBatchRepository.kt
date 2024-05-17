package com.yellowsunn.youtubetrending.repository

import com.yellowsunn.youtubetrending.domain.batch.YoutubeTrendingBatch
import org.springframework.data.jpa.repository.JpaRepository

interface YoutubeTrendingBatchRepository: JpaRepository<YoutubeTrendingBatch, Long> {
}