package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.ApiTest
import kr.happynewyear.api.authentication.dto.RefreshRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.application.service.TokenService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

class RefreshControllerTest : ApiTest() {

    @MockBean
    lateinit var tokenService: TokenService


    @Test
    fun refresh() {
        val refreshToken = "refreshToken"

        given(tokenService.refresh(refreshToken))
            .willReturn(TokenResult("accessToken", "newRefreshToken"))

        val request = RefreshRequest(refreshToken)
        val response = call(
            HttpMethod.POST, "/api/refresh", request,
            HttpStatus.OK, TokenResponse::class.java
        )

        assertThat(response).isNotNull
    }

}