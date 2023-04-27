package kr.happynewyear.notification.message

import kr.happynewyear.library.messaging.producer.SpringProducer
import org.springframework.stereotype.Component

@Component
class ApplicationDeadletterSendRequestProducer(
    private val brokerProducer: SpringProducer
) {

    fun produce(message: ApplicationDeadletterSendRequest) {
        brokerProducer.produce(message)
    }

}
