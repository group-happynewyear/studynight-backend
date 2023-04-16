package kr.happynewyear.api.authentication.controller

import io.jsonwebtoken.Jwts
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.constant.SocialAccountProvider
import kr.happynewyear.authentication.infrastructure.google.*
import kr.happynewyear.library.test.ApiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus.FOUND
import org.springframework.http.HttpStatus.OK
import java.util.*

class SocialLoginControllerTest(
    @Value("\${token.access.secret}") private val secret: String,
) : ApiTest() {

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
        val res = call(
            GET, "/api/social-login/callback/providers/$provider?code=$code",
            OK, TokenResponse::class.java
        )

        assertThat(res).isNotNull
    }


    @Test
    fun callback_user() {
        SocialAccountProvider.values().forEach { callback_user(it) }
    }

    private fun callback_user(provider: SocialAccountProvider) {
        val code = "authorization-code"
        val req = "/api/social-login/callback/providers/$provider?code=$code"
        val token1 = call(GET, req, OK, TokenResponse::class.java).accessToken
        val token2 = call(GET, req, OK, TokenResponse::class.java).accessToken

        assertThat(parseSub(token1)).isEqualTo(parseSub(token2))
    }

    private fun parseSub(jwt: String): String {
        val encodedSecret = Base64.getEncoder().encodeToString(secret.toByteArray())
        return Jwts.parser().setSigningKey(encodedSecret).parseClaimsJws(jwt).body.subject
    }

}
