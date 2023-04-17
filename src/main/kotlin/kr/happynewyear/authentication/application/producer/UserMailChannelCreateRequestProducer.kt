package kr.happynewyear.authentication.application.producer

import kr.happynewyear.library.message.BrokerProducer
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestProducer(
    private val brokerProducer: BrokerProducer
) {

    fun produce(userMailChannelCreateRequest: UserMailChannelCreateRequest) {
        brokerProducer.produce(userMailChannelCreateRequest)
    }

}
