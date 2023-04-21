package kr.happynewyear.notification.application.service

import kr.happynewyear.library.test.ApplicationTest
import kr.happynewyear.library.utility.Randoms
import kr.happynewyear.notification.application.exception.UserChannelNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class UserServiceTest : ApplicationTest() {

    @Autowired
    private lateinit var userService: UserService


    @Test
    fun createMailChannel() {
        val userId = Randoms.uuid()
        userService.createMailChannel(userId, "email@email.com")
    }


    @Test
    fun sendMail() {
        val userId = Randoms.uuid()
        userService.createMailChannel(userId, "email@email.com")

        userService.sendMail(userId, "title", "content")
    }

    @Test
    fun sendMail_channelNotFound() {
        val userId = Randoms.uuid()
        val action = { userService.sendMail(userId, "title", "content") }

        assertThrows<UserChannelNotFoundException>(action)
    }

}
