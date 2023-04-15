package kr.happynewyear.notification

import kr.happynewyear.library.notification.Notifier
import kr.happynewyear.notification.mail.MailSender
import kr.happynewyear.notification.slack.SlackSender
import org.springframework.stereotype.Component

@Component
class NotificationService(
    private val mailSender: MailSender,
    private val slackSender: SlackSender
) : Notifier {

    override fun mail(address: String, title: String, content: String) {
        mailSender.send(address, title, content)
    }

    override fun slack(address: String, message: String) {
        slackSender.send(address, message)
    }

}
