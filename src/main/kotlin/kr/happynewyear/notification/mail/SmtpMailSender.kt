package kr.happynewyear.notification.mail

import kr.happynewyear.library.constant.Profiles.Companion.LIVE
import kr.happynewyear.library.constant.Profiles.Companion.STAGE
import org.springframework.context.annotation.Profile
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
@Profile(STAGE, LIVE)
class SmtpMailSender(
    private val javaMailSender: JavaMailSender
) : MailSender {

    override fun send(address: String, title: String, content: String) {
        val message = SimpleMailMessage()
        message.setTo(address)
        message.subject = title
        message.text = content
        javaMailSender.send(message)
    }

}
