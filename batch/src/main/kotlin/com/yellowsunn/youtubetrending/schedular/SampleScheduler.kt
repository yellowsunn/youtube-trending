package com.yellowsunn.youtubetrending.schedular

import com.yellowsunn.youtubetrending.config.SampleJobConfiguration
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SampleScheduler(
    private val jobLauncher: JobLauncher,
    private val sampleJobConfiguration: SampleJobConfiguration,
) {
    @Scheduled(cron = "0 * * * * *")
    fun execute() {
        val jobParameters: JobParameters =
            JobParametersBuilder()
                .addLocalDateTime("dateTime", LocalDateTime.now())
                .toJobParameters()

        jobLauncher.run(sampleJobConfiguration.sampleJob(), jobParameters)
    }
}
