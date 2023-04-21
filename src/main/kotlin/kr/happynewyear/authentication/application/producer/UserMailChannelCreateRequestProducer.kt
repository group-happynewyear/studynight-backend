package kr.happynewyear.authentication.application.producer

import kr.happynewyear.library.messaging.producer.Producer
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestProducer : Producer<UserMailChannelCreateRequest>
