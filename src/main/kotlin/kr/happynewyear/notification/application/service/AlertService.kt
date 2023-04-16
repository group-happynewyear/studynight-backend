package kr.happynewyear.notification.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors.joining

@Service
@Transactional(readOnly = true)
class AlertService(
    private val notificationService: NotificationService
) {

    fun alert(applicationName: String, exceptionSummary: String, stacktrace: List<String>) {
        // get addr from channel service, if empty to default

        // TODO iter mail
        val title = "[$applicationName] $exceptionSummary"
        val content = stacktrace.stream().collect(joining("\n"))
        notificationService.mail("addr", title, content)

        // TODO iter slack
        val message = "[$applicationName] $exceptionSummary\n" +
            stacktrace.stream().filter { it.startsWith("kr.happynewyear") }.findFirst().orElseGet { stacktrace[0] }
        notificationService.slack("addr", message)
    }

    // TODO set default in channel service
    // @Value("\${alert.mail.address:}") private val mailAddress: String,
    // @Value("\${alert.slack.address:}") private val slackAddress: String,
}
