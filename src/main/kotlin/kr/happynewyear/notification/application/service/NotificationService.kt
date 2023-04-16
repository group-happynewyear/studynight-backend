package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.application.client.MailSender
import kr.happynewyear.notification.application.client.SlackSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NotificationService(
    private val mailSender: MailSender,
    private val slackSender: SlackSender
) {

    @Async
    fun mail(address: String, title: String, content: String) {
        mailSender.send(address, title, content)
    }

    @Async
    fun slack(address: String, message: String) {
        slackSender.send(address, message)
    }

}
