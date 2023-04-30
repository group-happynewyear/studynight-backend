package kr.happynewyear.api.authentication.controller

import com.ninjasquad.springmockk.SpykBean
import io.mockk.verify
import kr.happynewyear.authentication.constant.SocialAccountProvider
import kr.happynewyear.authentication.infrastructure.google.*
import kr.happynewyear.library.marshalling.jwt.JwtMarshallers
import kr.happynewyear.library.test.ApiTest
import kr.happynewyear.notification.message.UserMailChannelCreateRequestProducer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus.FOUND
import java.util.*

class SocialLoginControllerTest(
    @Value("\${security.token.access.secret}") private val secret: String,
) : ApiTest() {

    @SpykBean
    lateinit var userMailChannelCreateRequestProducer: UserMailChannelCreateRequestProducer


    @Test
    fun page() {
        SocialAccountProvider.values().forEach { page(it) }
    }

    private fun page(provider: SocialAccountProvider) {
        val redirect = redirect(
            GET, "/api/social-login/page/providers/$provider",
            FOUND
        )

        assertThat(redirect).isNotBlank
    }


    @Test
    fun callback_initial() {
        SocialAccountProvider.values().forEach { callback_initial(it) }
    }

    private fun callback_initial(provider: SocialAccountProvider) {
        val code = "authorization-code"
        val location = redirect(
            GET, "/api/social-login/callback/providers/$provider?code=$code",
            FOUND
        )

        assertThat(location).isNotNull
        verify { userMailChannelCreateRequestProducer.produce(any()) }
    }


    @Test
    fun callback_user() {
        SocialAccountProvider.values().forEach { callback_user(it) }
    }

    private fun callback_user(provider: SocialAccountProvider) {
        val req = "/api/social-login/callback/providers/$provider?code=code"
        val jwt1 = redirect(GET, req, FOUND).split("?")[1].split("&")[0].split("=")[1]
        val jwt2 = redirect(GET, req, FOUND).split("?")[1].split("&")[0].split("=")[1]

        assertThat(subjectOf(jwt1)).isEqualTo(subjectOf(jwt2))
        verify(exactly = 1) { userMailChannelCreateRequestProducer.produce(any()) }
    }

    private fun subjectOf(jwt: String): String {
        return JwtMarshallers.read(jwt, secret).subject
    }

}
