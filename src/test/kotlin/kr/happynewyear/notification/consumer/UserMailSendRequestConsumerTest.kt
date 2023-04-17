package kr.happynewyear.notification.consumer

import kr.happynewyear.library.test.ConsumerTest
import kr.happynewyear.library.utility.Randoms
import kr.happynewyear.notification.application.exception.UserChannelNotFoundException
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import kr.happynewyear.notification.message.UserMailSendRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class UserMailSendRequestConsumerTest : ConsumerTest() {

    @Autowired
    lateinit var userMailSendRequestConsumer: UserMailSendRequestConsumer


    @Autowired
    lateinit var userMailChannelCreateRequestConsumer: UserMailChannelCreateRequestConsumer


    private fun createUserMailChannel(userId: UUID, email: String) {
        userMailChannelCreateRequestConsumer.on(UserMailChannelCreateRequest(userId, email))
    }


    @Test
    fun on() {
        val userId = Randoms.uuid()
        createUserMailChannel(userId, "email@email.com")

        val message = UserMailSendRequest(userId, "title", "content")
        userMailSendRequestConsumer.on(message)
    }

    @Test
    fun on_channelNotFound() {
        val message = UserMailSendRequest(Randoms.uuid(), "title", "content")
        assertThrows<UserChannelNotFoundException> { userMailSendRequestConsumer.on(message) }
    }

}
