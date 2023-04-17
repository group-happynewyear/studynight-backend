package kr.happynewyear.notification.consumer

import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserMailChannelCreateRequestConsumer(
    private val userService: UserService
) {

    @EventListener
    fun on(m: UserMailChannelCreateRequest) {
        userService.createMailChannel(m.userId, m.email)
    }

}
