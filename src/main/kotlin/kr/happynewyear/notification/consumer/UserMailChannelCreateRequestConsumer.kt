package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.consumer.ConsumeProcessor
import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestConsumer(
    private val consumeProcessor: ConsumeProcessor,
    private val userService: UserService
) {

    @EventListener
    fun consume(message: UserMailChannelCreateRequest) {
        consumeProcessor.consume("createMailChannel", SPRING, message, {
            userService.createMailChannel(it.userId, it.email)
        })
    }

}
