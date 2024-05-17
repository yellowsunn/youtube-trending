package com.yellowsunn.youtubetrending.config

import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.domain.youtube.response.YoutubeVideo
import org.springframework.batch.item.database.AbstractPagingItemReader
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.min

class CustomItemReader(
    private val trendingHttpClient: YoutubeTrendingHttpClient,
    pageSize: Int,
) : AbstractPagingItemReader<YoutubeVideo>() {
    init {
        setPageSize(pageSize)
    }

    override fun doReadPage() {
        if (this.results == null) {
            this.results = CopyOnWriteArrayList()
        } else {
            results.clear()
        }

        val youtubeVideos = trendingHttpClient.findNowTrending().videos

        val startIdx = page * pageSize
        val subList = youtubeVideos.subList(startIdx, min(youtubeVideos.size, startIdx + pageSize))
        results.addAll(subList)
    }
}
