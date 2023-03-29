package kr.happynewyear.api.authentication.controller

import io.jsonwebtoken.Jwts
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.client.google.*
import kr.happynewyear.authentication.constant.SocialAccountProvider.GOOGLE
import kr.happynewyear.library.test.ApiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus.FOUND
import org.springframework.http.HttpStatus.OK
import java.util.*

class SocialLoginControllerTest(
    @Value("\${token.access.secret}") private val secret: String,
    @Value("\${social-login.google.login-page}") private val googleLoginPage: String,
    @Value("\${social-login.google.client.client-id}") private val googleClientId: String,
    @Value("\${social-login.google.client.client-secret}") private val googleClientSecret: String,
    @Value("\${social-login.google.client.scope}") private val googleScope: String,
    @Value("\${social-login.google.client.redirect-uri}") private val googleRedirectUri: String
) : ApiTest() {

    @Test
    fun page_google() {
        val provider = GOOGLE

        val redirect = redirect(
            GET, "/api/social-login/page/providers/$provider",
            FOUND
        )

        assertThat(redirect).startsWith(googleLoginPage)
        assertThat(redirect).contains(googleClientId)
        assertThat(redirect).doesNotContain(googleClientSecret)
        assertThat(redirect).contains(googleScope.replace(" ", "%20"))
        assertThat(redirect).contains(googleRedirectUri)
    }


    @Test
    fun callback_google_initial() {
        val provider = GOOGLE
        val code = "authorization-code"
        stubGoogleClient(code)

        val response = call(
            GET, "/api/social-login/callback/providers/$provider?code=$code",
            OK, TokenResponse::class.java
        )

        assertThat(response).isNotNull
    }

    @Test
    fun callback_google_user() {
        val provider = GOOGLE
        val code = "authorization-code"
        stubGoogleClient(code)

        val req = "/api/social-login/callback/providers/$provider?code=$code"
        val token1 = call(GET, req, OK, TokenResponse::class.java).accessToken
        val token2 = call(GET, req, OK, TokenResponse::class.java).accessToken

        assertThat(parseSub(token1)).isEqualTo(parseSub(token2))
    }

    private fun parseSub(jwt: String): String {
        val encodedSecret = Base64.getEncoder().encodeToString(secret.toByteArray())
        return Jwts.parser().setSigningKey(encodedSecret).parseClaimsJws(jwt).body.subject
    }


    @MockBean
    lateinit var googleTokenClient: GoogleTokenClient

    @MockBean
    lateinit var googleUserinfoClient: GoogleUserinfoClient

    private fun stubGoogleClient(code: String) {
        val accessToken = "access_token"
        val tokenReq = GoogleTokenRequest(code, googleClientId, googleClientSecret, googleScope, googleRedirectUri)
        val tokenRes = GoogleTokenResponse(accessToken, "", "", "", "", 0L)
        given(googleTokenClient.exchange(tokenReq)).willReturn(tokenRes)

        val userinfo = GoogleUserinfoResponse("googleUserId", "", true, "", "", "", "", "")
        given(googleUserinfoClient.exchange("Bearer $accessToken")).willReturn(userinfo)
    }

}
