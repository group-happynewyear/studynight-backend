package kr.happynewyear.library.exception

import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AlertSender(
    @Value("\${spring.application.name}") private val applicationName: String,
    private val applicationAlertSendRequestProducer: ApplicationAlertSendRequestProducer
) {

    fun send(e: Exception) {
        val exceptionType = e.javaClass.simpleName
        val exceptionMessage = e.message ?: ""
        val stacktrace = e.stackTrace.map { it.toString() }.toList()
        val message = ApplicationAlertSendRequest(applicationName, exceptionType, exceptionMessage, stacktrace)
        applicationAlertSendRequestProducer.produce(message)
    }

}
