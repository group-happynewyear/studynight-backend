package kr.happynewyear.library.notification.mail

import kr.happynewyear.library.constant.Profiles
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles(Profiles.TEST)
class MailSenderTest {

    @Autowired
    lateinit var mailSender: MailSender


    @Test
    fun send() {
        val email = "email@email.com"
        mailSender.send(email, "title", "content")
    }

}
