package com.yellowsunn.youtubetrending.domain.batch

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class YoutubeTrendingBatch(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val createAt: LocalDateTime,
    var completed: Boolean = false,
) {
    fun complete() {
        this.completed = true
    }
}
