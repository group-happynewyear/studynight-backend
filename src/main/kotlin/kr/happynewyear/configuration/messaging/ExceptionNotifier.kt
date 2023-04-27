package kr.happynewyear.configuration.messaging

import kr.happynewyear.library.exception.ExceptionNotifier
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import kr.happynewyear.notification.message.ApplicationAlertSendRequestProducer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ExceptionNotifier(
    private val applicationAlertSendRequestProducer: ApplicationAlertSendRequestProducer,
    @Value("\${spring.application.name}") private val applicationName: String
) : ExceptionNotifier {

    override fun send(e: Exception) {
        val message = ApplicationAlertSendRequest.of(applicationName, e)
        applicationAlertSendRequestProducer.produce(message)
    }

}
