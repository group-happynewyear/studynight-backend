package kr.happynewyear.notification.message

import kr.happynewyear.library.messaging.producer.SpringProducer
import org.springframework.stereotype.Component

@Component
class ApplicationAlertSendRequestProducer(
    private val brokerProducer: SpringProducer
) {

    fun produce(message: ApplicationAlertSendRequest) {
        brokerProducer.produce(message)
    }

}
