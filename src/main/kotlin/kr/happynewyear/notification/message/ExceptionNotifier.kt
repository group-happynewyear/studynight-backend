package kr.happynewyear.notification.message

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ExceptionNotifier(
    private val applicationAlertSendRequestProducer: ApplicationAlertSendRequestProducer,
    @Value("\${spring.application.name}") private val applicationName: String
) {

    fun send(e: Exception) {
        val message = ApplicationAlertSendRequest.of(applicationName, e)
        applicationAlertSendRequestProducer.produce(message)
    }

}
