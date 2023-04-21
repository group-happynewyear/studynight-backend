package kr.happynewyear.notification.application.service

import kr.happynewyear.library.exception.ExceptionHandlers
import kr.happynewyear.notification.application.client.MailSender
import kr.happynewyear.notification.application.client.SlackSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Consumer

@Service
@Transactional(readOnly = true)
class NotificationService(
    private val mailSender: MailSender,
    private val slackSender: SlackSender
) {

    @Async
    fun mail(
        address: String, title: String, content: String,
        onFail: Consumer<Exception>
    ) {
        ExceptionHandlers.run({ mailSender.send(address, title, content) }, onFail)
    }

    @Async
    fun slack(
        address: String, message: String,
        onFail: Consumer<Exception>
    ) {
        ExceptionHandlers.run({ slackSender.send(address, message) }, onFail)
    }

}
