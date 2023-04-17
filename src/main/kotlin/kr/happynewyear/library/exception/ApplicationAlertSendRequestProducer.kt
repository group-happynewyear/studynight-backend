package kr.happynewyear.library.exception

import kr.happynewyear.library.message.Producer
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class ApplicationAlertSendRequestProducer(
    private val producer: Producer,
    @Value("\${spring.application.name}") private val applicationName: String,
) {

    @Async
    fun send(e: Exception) {
        val exceptionType = e.javaClass.simpleName
        val exceptionMessage = e.message ?: ""
        val stacktrace = e.stackTrace.map { it.toString() }.toList()
        val message = ApplicationAlertSendRequest(applicationName, exceptionType, exceptionMessage, stacktrace)
        producer.produce(message)
    }

}
