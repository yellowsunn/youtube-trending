package com.yellowsunn.youtubetrending

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

//@EnableScheduling
@SpringBootApplication
class YoutubeTrendingBatchApplication

fun main(args: Array<String>) {
    runApplication<YoutubeTrendingBatchApplication>(*args)
}
