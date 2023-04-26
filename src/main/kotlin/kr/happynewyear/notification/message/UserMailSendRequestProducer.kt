package kr.happynewyear.notification.message

import kr.happynewyear.library.messaging.producer.SpringProducer
import org.springframework.stereotype.Component

@Component
class UserMailSendRequestProducer(
    private val springProducer: SpringProducer
) {

    fun produce(message: UserMailSendRequest) {
        springProducer.produce(message)
    }

}
