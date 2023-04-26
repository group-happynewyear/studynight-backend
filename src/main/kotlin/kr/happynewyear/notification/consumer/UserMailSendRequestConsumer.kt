package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.consumer.ConsumeProcessor
import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailSendRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserMailSendRequestConsumer(
    private val consumeProcessor: ConsumeProcessor,
    private val userService: UserService
) {

    @EventListener
    fun consume(message: UserMailSendRequest) {
        consumeProcessor.consume {
            userService.sendMail(message.userId, message.title, message.content)
        }
    }

}
