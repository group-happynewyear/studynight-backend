package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.consumer.Consumer
import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailSendRequest
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UserMailSendRequestConsumer(
    private val userService: UserService
) : Consumer<UserMailSendRequest> {

    @Async
    @EventListener
    override fun consume(message: UserMailSendRequest) {
        userService.sendMail(message.userId, message.title, message.content)
    }

}
