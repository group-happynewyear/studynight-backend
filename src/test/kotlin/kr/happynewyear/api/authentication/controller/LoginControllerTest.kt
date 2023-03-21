package kr.happynewyear.api.authentication.controller

import kr.happynewyear.library.test.ApiTest
import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import kr.happynewyear.api.authentication.dto.LoginRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.*

class LoginControllerTest : ApiTest() {

    @Test
    fun login() {
        val email = "email@email.com"
        val password = "password"

        run(POST, "/api/accounts", AccountCreateRequest(email, password), CREATED)

        val request = LoginRequest(email, password)
        val response = call(
            POST, "/api/login", request,
            OK, TokenResponse::class.java
        )

        assertThat(response.accessToken).isNotNull
        assertThat(response.refreshToken).isNotNull
    }

    @Test
    fun login_notExistAccount() {
        run(
            POST, "/api/login", LoginRequest("email@email.com", "password"),
            UNAUTHORIZED
        )
    }

    @Test
    fun login_invalidPassword() {
        val email = "email@email.com"
        run(POST, "/api/accounts", AccountCreateRequest(email, "password"), CREATED)

        run(
            POST, "/api/login", LoginRequest(email, "wrong"),
            UNAUTHORIZED
        )
    }

}
