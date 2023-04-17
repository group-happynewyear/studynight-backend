package kr.happynewyear.authentication.application.producer

import kr.happynewyear.library.message.Producer
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestProducer(
    private val producer: Producer
) {

    fun produce(userMailChannelCreateRequest: UserMailChannelCreateRequest) {
        producer.produce(userMailChannelCreateRequest)
    }

}
