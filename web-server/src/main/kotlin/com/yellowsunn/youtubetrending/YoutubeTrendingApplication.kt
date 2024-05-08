package com.yellowsunn.youtubetrending

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class YoutubeTrendingApplication

fun main(args: Array<String>) {
    runApplication<YoutubeTrendingApplication>(*args)
}
