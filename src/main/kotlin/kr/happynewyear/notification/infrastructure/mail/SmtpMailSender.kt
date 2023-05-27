package kr.happynewyear.notification.infrastructure.mail

import kr.happynewyear.library.constant.Profiles.Companion.LIVE
import kr.happynewyear.library.constant.Profiles.Companion.STAGE
import kr.happynewyear.notification.application.client.MailSender
import org.springframework.context.annotation.Profile
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_8

@Component
@Profile(STAGE, LIVE)
class SmtpMailSender(
    private val javaMailSender: JavaMailSender
) : MailSender {

    override fun send(address: String, title: String, content: String) {
        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, UTF_8.name())
        helper.setTo(address)
        helper.setSubject(title)
        helper.setText(content, true)
        javaMailSender.send(message)
    }

}
