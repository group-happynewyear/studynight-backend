package kr.happynewyear.authentication.batch.scheduler

import kr.happynewyear.library.exception.ExceptionNotifier
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TokenClearScheduler(
    private val jobLauncher: JobLauncher,
    private val tokenClearJob: Job,
    private val exceptionNotifier: ExceptionNotifier
) {

    @Scheduled(cron = "0 0 4 * * *") // every 4:00
    fun schedule() {
        try {
            jobLauncher.run(tokenClearJob, param())
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
