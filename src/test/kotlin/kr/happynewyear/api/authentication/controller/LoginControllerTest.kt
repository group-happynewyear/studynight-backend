package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.ApiTest
import kr.happynewyear.api.authentication.dto.LoginRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.application.service.AccountService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.OK

class LoginControllerTest : ApiTest() {

    @MockBean
    lateinit var accountService: AccountService


    @Test
    fun login() {
        val email = "email@email.com"
        val password = "password"

        given(accountService.login(email, password))
            .willReturn(TokenResult("accessToken", "refreshToken"))

        val request = LoginRequest(email, password)
        val response = call(
            POST, "/api/login", request,
            OK, TokenResponse::class.java
        )

        assertThat(response).isNotNull
    }

}