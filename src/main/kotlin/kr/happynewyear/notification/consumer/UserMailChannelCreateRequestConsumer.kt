package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.consumer.Consumer
import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestConsumer(
    private val userService: UserService
) : Consumer<UserMailChannelCreateRequest> {

    @Async
    @EventListener
    override fun consume(message: UserMailChannelCreateRequest) {
        userService.createMailChannel(message.userId, message.email)
    }

}
