package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.consumer.Consume
import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestConsumer(
    private val userService: UserService
) {

    @EventListener
    @Consume("createMailChannel", SPRING)
    fun consume(message: UserMailChannelCreateRequest) {
        userService.createMailChannel(message.userId, message.email)
    }

}
