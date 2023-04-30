package kr.happynewyear.api.authentication.controller

import com.ninjasquad.springmockk.SpykBean
import io.mockk.verify
import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import kr.happynewyear.api.authentication.dto.LoginRequest
import kr.happynewyear.api.authentication.dto.RefreshRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.service.TokenService
import kr.happynewyear.library.test.ApiTest
import kr.happynewyear.library.utility.Randoms
import kr.happynewyear.application.producer.ApplicationAlertSendRequestProducer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.*
import org.springframework.test.util.ReflectionTestUtils

class RefreshControllerTest : ApiTest() {

    @Autowired
    lateinit var tokenService: TokenService

    @SpykBean
    lateinit var applicationAlertSendRequestProducer: ApplicationAlertSendRequestProducer


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

    @Test
    fun refresh_malformedUuid() {
        val refreshToken = "malformed_uuid"

        run(
            POST, "/api/refresh", RefreshRequest(refreshToken),
            BAD_REQUEST
        )
    }

    @Test
    fun refresh_notExist() {
        val refreshToken = Randoms.uuidString()

        run(
            POST, "/api/refresh", RefreshRequest(refreshToken),
            UNAUTHORIZED
        )
    }

    @Test
    fun refresh_expired() {
        val email = "email@email.com"
        val password = "password"
        run(POST, "/api/accounts", AccountCreateRequest(email, password), CREATED)

        ReflectionTestUtils.setField(tokenService, "expirationDays", 0)
        val token = call(POST, "/api/login", LoginRequest(email, password), OK, TokenResponse::class.java)

        run(
            POST, "/api/refresh", RefreshRequest(token.refreshToken),
            UNAUTHORIZED
        )
    }

    @Test
    fun refresh_reused() {
        val email = "email@email.com"
        val password = "password"
        run(POST, "/api/accounts", AccountCreateRequest(email, password), CREATED)

        // login
        val t1 = call(POST, "/api/login", LoginRequest(email, password), OK, TokenResponse::class.java).refreshToken
        // valid refresh
        val t2 = call(POST, "/api/refresh", RefreshRequest(t1), OK, TokenResponse::class.java).refreshToken

        // reuse
        run(POST, "/api/refresh", RefreshRequest(t1), UNAUTHORIZED)

        // valid after reuse
        run(POST, "/api/refresh", RefreshRequest(t2), UNAUTHORIZED)

        verify { applicationAlertSendRequestProducer.produce(any()) }
    }

}
