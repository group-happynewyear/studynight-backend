package kr.happynewyear.notification.consumer

import kr.happynewyear.library.test.ConsumerTest
import kr.happynewyear.library.utility.Randoms
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class UserMailChannelCreateRequestConsumerTest : ConsumerTest() {

    @Autowired
    lateinit var userMailChannelCreateRequestConsumer: UserMailChannelCreateRequestConsumer


    @Test
    fun on() {
        val message = UserMailChannelCreateRequest(Randoms.uuid(), "email@email.com")
        userMailChannelCreateRequestConsumer.on(message)
    }

}
