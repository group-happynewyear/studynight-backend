package kr.happynewyear.studynight.application.producer

import kr.happynewyear.library.message.Producer
import kr.happynewyear.notification.message.UserMailSendRequest
import org.springframework.stereotype.Component

@Component
class UserMailSendRequestProducer(
    private val producer: Producer
) {

    fun produce(userMailSendRequest: UserMailSendRequest) {
        producer.produce(userMailSendRequest)
    }

}
