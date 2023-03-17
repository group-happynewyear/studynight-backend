package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.ApiTest
import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import kr.happynewyear.api.authentication.dto.LoginRequest
import kr.happynewyear.api.authentication.dto.RefreshRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK

class RefreshControllerTest : ApiTest() {

    @Test
    fun refresh() {
        val email = "email@email.com"
        val password = "password"
        run(POST, "/api/accounts", AccountCreateRequest(email, password), CREATED)
        val token = call(POST, "/api/login", LoginRequest(email, password), OK, TokenResponse::class.java)

        val request = RefreshRequest(token.refreshToken)
        val response = call(
            POST, "/api/refresh", request,
            OK, TokenResponse::class.java
        )

        assertThat(response.refreshToken).isNotEqualTo(token.refreshToken)
    }

    // TODO notfound
    // TODO expired
    // TODO reuse
}
