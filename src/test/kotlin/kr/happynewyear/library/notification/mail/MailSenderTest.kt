package kr.happynewyear.library.notification.mail

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MailSenderTest {

    @Autowired
    lateinit var mailSender: MailSender


    @Test
    fun send() {
        val email = "email@email.com"
        mailSender.send(email, "title", "content")
    }

}
