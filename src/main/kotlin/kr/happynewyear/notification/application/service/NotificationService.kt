package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.application.client.MailSender
import kr.happynewyear.notification.application.client.SlackSender
import kr.happynewyear.notification.application.dto.Notification
import kr.happynewyear.notification.constant.AddressType.MAIL
import kr.happynewyear.notification.constant.AddressType.SLACK
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NotificationService(
    private val mailSender: MailSender,
    private val slackSender: SlackSender
) {

    fun notify(notification: Notification) = with(notification) {
        when (address.type) {
            MAIL -> mailSender.send(address.value, title, content)
            SLACK -> slackSender.send(address.value, message)
        }
    }

}
