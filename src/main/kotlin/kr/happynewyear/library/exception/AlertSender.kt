package kr.happynewyear.library.exception

import jakarta.annotation.PostConstruct
import kr.happynewyear.library.notification.Notifier
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors.joining

@Component
class AlertSender(
    private val notifier: Notifier,
    @Value("\${spring.application.name}") private val applicationName: String,
    @Value("\${alert.mail.address:}") private val mailAddress: String,
    @Value("\${alert.slack.address:}") private val slackAddress: String,
) {

    private val log = LoggerFactory.getLogger(this::class.java)


    @PostConstruct
    fun warnToSetAlert() {
        if (mailAddress.isBlank() && slackAddress.isBlank())
            log.warn("Set property \${alert.mail.address} or \${alert.slack.address}")

        if (mailAddress.isNotBlank()) log.info("$mailAddress is set as alert address.")
        if (slackAddress.isNotBlank()) log.info("$slackAddress is set as alert address.")
    }


    @Async
    fun sendAsync(e: Exception) {
        val head = "[$applicationName] ${e.javaClass.simpleName}: ${e.message}"

        if (mailAddress.isNotBlank()) notifier.mailAsync(mailAddress, head, fullStackTrace(e))
        if (slackAddress.isNotBlank()) notifier.slackAsync(slackAddress, "$head\n${coreStackTrace(e)}")
    }

    private fun fullStackTrace(e: Exception): String {
        return Arrays.stream(e.stackTrace)
            .map { it.toString() }
            .collect(joining("\n"))
    }

    private fun coreStackTrace(e: Exception): String {
        return Arrays.stream(e.stackTrace)
            .filter { it.className.startsWith("kr.happynewyear") }.findFirst()
            .map { it.toString() }
            .orElseGet { e.stackTrace[0].toString() }
    }

}
