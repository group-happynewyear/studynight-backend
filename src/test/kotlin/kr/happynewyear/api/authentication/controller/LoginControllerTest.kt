package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.ApiTest
import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import kr.happynewyear.api.authentication.dto.LoginRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK

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

        assertThat(response).isNotNull
        // TODO password encoded
    }

    // TODO accountNotFound

}
