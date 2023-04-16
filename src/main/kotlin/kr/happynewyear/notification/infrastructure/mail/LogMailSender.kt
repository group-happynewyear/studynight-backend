package kr.happynewyear.notification.infrastructure.mail

import kr.happynewyear.library.constant.Profiles.Companion.LOCAL
import kr.happynewyear.library.constant.Profiles.Companion.TEST
import kr.happynewyear.notification.application.client.MailSender
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(TEST, LOCAL)
class LogMailSender : MailSender {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun send(address: String, title: String, content: String) {
        log.info(
            "\n" +
                "- address: $address\n" +
                "- title: $title\n" +
                "$content\n" +
                "=== eof."
        )
    }

}
