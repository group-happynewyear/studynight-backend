package kr.happynewyear.authentication.application.producer

import kr.happynewyear.library.messaging.producer.SpringProducer
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestProducer(
    private val brokerProducer: SpringProducer
) {

    fun produce(message: UserMailChannelCreateRequest) {
        brokerProducer.produce(message)
    }

}
