package kr.happynewyear.library.notification.slack

import kr.happynewyear.library.constant.Profiles.Companion.LOCAL
import kr.happynewyear.library.constant.Profiles.Companion.TEST
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(TEST, LOCAL)
class LogSlackSender : SlackSender {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun send(address: String, message: String) {
        log.info(
            "\n" +
                "- address: $address\n" +
                "$message\n" +
                "===eof."
        )
    }

}
