package kr.happynewyear.application.producer

import kr.happynewyear.library.messaging.producer.SpringProducer
import kr.happynewyear.notification.message.ApplicationDeadletterSendRequest
import org.springframework.stereotype.Component

@Component
class ApplicationDeadletterSendRequestProducer(
    private val brokerProducer: SpringProducer
) {

    fun produce(message: ApplicationDeadletterSendRequest) {
        brokerProducer.produce(message)
    }

}
