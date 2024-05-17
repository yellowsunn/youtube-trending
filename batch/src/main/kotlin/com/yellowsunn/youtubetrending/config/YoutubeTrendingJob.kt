package com.yellowsunn.youtubetrending.config

import com.yellowsunn.youtubetrending.domain.batch.YoutubeTrendingBatch
import com.yellowsunn.youtubetrending.repository.YoutubeTrendingBatchRepository
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import java.time.LocalDateTime

@Configuration
class YoutubeTrendingJob(
    private val jobRepository: JobRepository,
    private val tx: PlatformTransactionManager,
    private val youtubeTrendingBatchRepository: YoutubeTrendingBatchRepository,
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun youtubeBatchJob(): Job {
        return JobBuilder("youtubeBatchJob", jobRepository)
            .start(createBatchHistoryStep())
//            .next(fetchYoutubeTrendingDataStep())
//            .next(updateBatchHistoryToCompleteStep())
            .build()
    }

    @Bean
    fun createBatchHistoryStep(): Step {
        return StepBuilder("createBatchHistoryStep", jobRepository)
            .tasklet({ _, chunkContext: ChunkContext ->

                // logic
                val entity = YoutubeTrendingBatch(createAt = LocalDateTime.now())
                youtubeTrendingBatchRepository.save(entity)

                chunkContext.stepContext.stepExecution.executionContext.put("batchHistoryId", entity.id)

                RepeatStatus.FINISHED
            }, tx)
            .build()
    }

//    @Bean
//    fun fetchYoutubeTrendingDataStep(): Step {
//
//    }
//
//    @Bean
//    fun updateBatchHistoryToCompleteStep(): Step {
//
//    }
}
