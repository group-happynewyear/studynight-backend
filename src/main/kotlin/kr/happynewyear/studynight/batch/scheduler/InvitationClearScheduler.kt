package kr.happynewyear.studynight.batch.scheduler

import kr.happynewyear.library.exception.ExceptionNotifier
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class InvitationClearScheduler(
    private val jobLauncher: JobLauncher,
    private val invitationClearJob: Job,
    private val exceptionNotifier: ExceptionNotifier
) {

    @Scheduled(cron = "0 0 4 * * *") // every 4:00
    fun schedule() {
        try {
            jobLauncher.run(invitationClearJob, param())
        } catch (e: Exception) {
            exceptionNotifier.send(e)
        }
    }

    private fun param(): JobParameters {
        return JobParametersBuilder()
            .addLocalDate("localDate", LocalDate.now())
            .toJobParameters()
    }

}
