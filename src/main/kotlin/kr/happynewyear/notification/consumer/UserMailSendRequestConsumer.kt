package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.consumer.Consume
import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailSendRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserMailSendRequestConsumer(
    private val userService: UserService
) {

    @EventListener
    @Consume("sendMail", SPRING)
    fun consume(message: UserMailSendRequest) {
        userService.sendMail(message.userId, message.title, message.content)
    }

}
