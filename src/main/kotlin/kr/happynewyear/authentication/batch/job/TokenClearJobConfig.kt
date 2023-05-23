package kr.happynewyear.authentication.batch.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class TokenClearJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager
) {

    @Bean
    fun tokenClearJob(tokenClearStep: Step): Job {
        return JobBuilder("tokenClearJob", jobRepository)
            .start(tokenClearStep)
            .build()
    }

    @Bean
    fun tokenClearStep(tokenClearTasklet: Tasklet): Step {
        return StepBuilder("tokenClearStep", jobRepository)
            .tasklet(tokenClearTasklet, transactionManager)
            .build()
    }

}