package kr.happynewyear.notification.message

import kr.happynewyear.library.messaging.producer.SpringProducer
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestProducer(
    private val brokerProducer: SpringProducer
) {

    fun produce(message: UserMailChannelCreateRequest) {
        brokerProducer.produce(message)
    }

}
