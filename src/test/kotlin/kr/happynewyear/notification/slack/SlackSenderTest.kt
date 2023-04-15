package kr.happynewyear.notification.slack

import kr.happynewyear.notification.slack.SlackSender
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SlackSenderTest {

    @Autowired
    lateinit var slackSender: SlackSender


    @Test
    fun send() {
        val url = "https://hooks.slack.com/services/etc"
        slackSender.send(url, "message")
    }

}
