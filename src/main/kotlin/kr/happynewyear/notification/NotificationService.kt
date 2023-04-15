package kr.happynewyear.notification

import kr.happynewyear.notification.mail.MailSender
import kr.happynewyear.notification.slack.SlackSender
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
