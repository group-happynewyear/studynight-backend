package kr.happynewyear.studynight.batch.job

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
class InvitationClearJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager
) {

    @Bean
    fun invitationClearJob(invitationClearStep: Step): Job {
        return JobBuilder("invitationClearJob", jobRepository)
            .start(invitationClearStep)
            .build()
    }

    @Bean
    fun invitationClearStep(invitationClearTasklet: Tasklet): Step {
        return StepBuilder("invitationClearStep", jobRepository)
            .tasklet(invitationClearTasklet, transactionManager)
            .build()
    }

}