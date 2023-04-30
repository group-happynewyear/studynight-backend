package kr.happynewyear.application.producer

import kr.happynewyear.library.messaging.producer.SpringProducer
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.stereotype.Component

@Component
class ApplicationAlertSendRequestProducer(
    private val brokerProducer: SpringProducer
) {

    fun produce(message: ApplicationAlertSendRequest) {
        brokerProducer.produce(message)
    }

}
