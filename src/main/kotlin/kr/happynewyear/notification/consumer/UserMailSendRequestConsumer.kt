package kr.happynewyear.notification.consumer

import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailSendRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserMailSendRequestConsumer(
    private val userService: UserService
) {

    @EventListener
    fun on(m: UserMailSendRequest) {
        userService.sendMail(m.userId, m.title, m.content)
    }

}
