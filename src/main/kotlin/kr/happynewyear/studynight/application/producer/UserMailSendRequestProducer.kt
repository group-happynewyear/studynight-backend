package kr.happynewyear.studynight.application.producer

import kr.happynewyear.library.message.BrokerProducer
import kr.happynewyear.notification.message.UserMailSendRequest
import org.springframework.stereotype.Component

@Component
class UserMailSendRequestProducer(
    private val brokerProducer: BrokerProducer
) {

    fun produce(userMailSendRequest: UserMailSendRequest) {
        brokerProducer.produce(userMailSendRequest)
    }

}
