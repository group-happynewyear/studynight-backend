package kr.happynewyear.notification.consumer

import com.github.josh910830.portablemq.core.consumer.Consume
import com.github.josh910830.portablemq.core.consumer.Consumer
import com.github.josh910830.portablemq.spring.consumer.SpringListener
import kr.happynewyear.library.constant.Topics.Companion.USER_MAIL_SEND_REQUEST
import kr.happynewyear.notification.application.service.UserService
import kr.happynewyear.notification.message.UserMailSendRequest

@Consumer
class UserMailSendRequestConsumer(
    private val userService: UserService
) {

    @Consume
    @SpringListener(USER_MAIL_SEND_REQUEST)
    fun consume(message: UserMailSendRequest) {
        userService.sendMail(message.userId, message.title, message.content)
    }

}
