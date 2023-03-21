package kr.happynewyear.api.authentication.controller

import kr.happynewyear.library.test.ApiTest
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.application.service.SocialAccountService
import kr.happynewyear.authentication.constant.SocialAccountProvider.GOOGLE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus.FOUND
import org.springframework.http.HttpStatus.OK
import java.util.*

class SocialLoginControllerTest : ApiTest() {

    @MockBean
    lateinit var socialAccountService: SocialAccountService


    @Test
    fun page() {
        val provider = GOOGLE

        given(socialAccountService.locatePage(provider))
            .willReturn("location")

        socialAccountService.locatePage(provider)

        val redirect = redirect(
            GET, "/api/social-login/page/providers/$provider",
            FOUND
        )

        assertThat(redirect).isNotNull
    }

    @Test
    fun callback() {
        val provider = GOOGLE
        val code = "authorization-code"

        given(socialAccountService.login(provider, code))
            .willReturn(TokenResult("accessToken", UUID.randomUUID()))

        val response = call(
            GET, "/api/social-login/callback/providers/$provider?code=$code",
            OK, TokenResponse::class.java
        )

        assertThat(response).isNotNull
    }

}