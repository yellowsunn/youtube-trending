package com.yellowsunn.youtubetrending.config

import com.yellowsunn.youtubetrending.domain.batch.YoutubeTrendingBatch
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeTrendingHttpClient
import com.yellowsunn.youtubetrending.domain.youtube.YoutubeVideoEntity
import com.yellowsunn.youtubetrending.domain.youtube.response.YoutubeVideo
import com.yellowsunn.youtubetrending.repository.YoutubeTrendingBatchRepository
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import java.time.LocalDateTime
import javax.sql.DataSource

@Configuration
class YoutubeTrendingJob(
    private val jobRepository: JobRepository,
    private val tx: PlatformTransactionManager,
    private val youtubeTrendingBatchRepository: YoutubeTrendingBatchRepository,
    private val youtubeTrendingHttpClient: YoutubeTrendingHttpClient,
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun youtubeBatchJob(
        fetchYoutubeTrendingDataStep: Step,
        updateBatchHistoryToCompleteStep: Step,
    ): Job {
        return JobBuilder("youtubeBatchJob", jobRepository)
            .start(createBatchHistoryStep())
            .next(fetchYoutubeTrendingDataStep)
            .next(updateBatchHistoryToCompleteStep)
            .build()
    }

    @Bean
    fun createBatchHistoryStep(): Step {
        return StepBuilder("createBatchHistoryStep", jobRepository)
            .tasklet({ _, chunkContext: ChunkContext ->

                // logic
                val entity = YoutubeTrendingBatch(createAt = LocalDateTime.now())
                youtubeTrendingBatchRepository.save(entity)

                chunkContext.stepContext.stepExecution.jobExecution.executionContext.putLong(
                    "batchHistoryId",
                    entity.id,
                )

                RepeatStatus.FINISHED
            }, tx)
            .build()
    }

    @Bean
    @JobScope
    fun updateBatchHistoryToCompleteStep(
        @Value("#{jobExecutionContext['batchHistoryId']}") batchHistoryId: Long,
    ): Step {
        return StepBuilder("updateBatchHistoryToCompleteStep", jobRepository)
            .tasklet({ _, _ ->
                // logic
                val entity = youtubeTrendingBatchRepository.findById(batchHistoryId).orElseThrow()
                entity.complete()

                youtubeTrendingBatchRepository.save(entity)

                RepeatStatus.FINISHED
            }, tx)
            .build()
    }

    @JobScope
    @Bean
    fun fetchYoutubeTrendingDataStep(
        @Value("#{jobExecutionContext['batchHistoryId']}") batchHistoryId: Long,
        youtubeItemProcessor: ItemProcessor<YoutubeVideo, YoutubeVideoEntity>,
        youtubeItemWriter: JdbcBatchItemWriter<YoutubeVideoEntity>,
    ): Step {
        val reader = CustomItemReader(trendingHttpClient = youtubeTrendingHttpClient, pageSize = 50)

        return StepBuilder("fetchYoutubeTrendingDataStep", jobRepository)
            .chunk<YoutubeVideo, YoutubeVideoEntity>(50, tx)
            .reader(reader)
            .processor(youtubeItemProcessor)
            .writer(youtubeItemWriter)
            .build()
    }

    @Bean
    @JobScope
    fun youtubeItemProcessor(
        @Value("#{jobExecutionContext['batchHistoryId']}") batchHistoryId: Long,
    ): ItemProcessor<YoutubeVideo, YoutubeVideoEntity> {
        return ItemProcessor { input: YoutubeVideo ->
            input.toEntity(batchHistoryId)
        }
    }

    @Bean
    @JobScope
    fun youtubeItemWriter(dataSource: DataSource): JdbcBatchItemWriter<YoutubeVideoEntity> {
        return JdbcBatchItemWriterBuilder<YoutubeVideoEntity>()
            .dataSource(dataSource)
            .sql(
                "INSERT INTO youtube_video(view_count, youtube_trending_batch_id, channel_handle, channel_id, channel_thumbnail, channel_title, description, length_text, published_time_text, rich_thumbnail, thumbnail, title, video_id, video_type) " +
                    "VALUES (:viewCount, :youtubeTrendingBatchId, :channelHandle, :channelId, :channelThumbnail, :channelTitle, :description, :lengthText, :publishedTimeText, :richThumbnail, :thumbnail, :title, :videoId, :videoType)",
            )
            .beanMapped()
            .build()
    }
}
