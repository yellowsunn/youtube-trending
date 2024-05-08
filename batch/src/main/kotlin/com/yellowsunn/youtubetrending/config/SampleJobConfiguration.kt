package com.yellowsunn.youtubetrending.config

import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class SampleJobConfiguration {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun sampleJob(
        jobRepository: JobRepository,
        sampleStep1: Step,
        sampleStep2: Step,
    ): Job {
        return JobBuilder("helloJob", jobRepository)
            .start(sampleStep1)
            .next(sampleStep2)
            .build()
    }

    @Bean
    fun sampleStep1(
        jobRepository: JobRepository,
        tx: PlatformTransactionManager,
    ): Step {
        return StepBuilder("sampleStep1", jobRepository)
            .tasklet({ _, _ ->
                logger.info("sampleStep1 > hello world!")
                RepeatStatus.FINISHED
            }, tx)
            .build()
    }

    @Bean
    fun sampleStep2(
        jobRepository: JobRepository,
        tx: PlatformTransactionManager,
    ): Step {
        return StepBuilder("sampleStep2", jobRepository)
            .tasklet({ _, _ ->
                logger.info("sampleStep2 > hello world!")
                RepeatStatus.FINISHED
            }, tx)
            .build()
    }
}
