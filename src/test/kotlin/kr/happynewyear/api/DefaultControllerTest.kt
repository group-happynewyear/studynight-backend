package kr.happynewyear.api

import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import kr.happynewyear.api.authentication.dto.LoginRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.infrastructure.database.AccountJpaRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.*
import java.util.*

class DefaultControllerTest(
    @Value("\${spring.application.name}") private val applicationName: String,
    @Value("\${spring.profiles.active}") private val activeProfiles: String
) : ApiTest() {

    @Autowired
    lateinit var accountJpaRepository: AccountJpaRepository


    @Test
    fun healthCheck() {
        val response = call(
            GET, "/api/health-check",
            OK
        )

        assertThat(response).isEqualTo("[$applicationName] is on [$activeProfiles]")
    }


    @Test
    fun hello() {
        val email = "email@email.com"
        val password = "password"
        val location = redirect(POST, "/api/accounts", AccountCreateRequest(email, password), CREATED)
        val token = call(POST, "/api/login", LoginRequest(email, password), OK, TokenResponse::class.java)

        val response = call(
            GET, "/api/hello", token.accessToken,
            OK
        )

        val accountId = location.replace("/api/accounts/", "")
        val userId = accountJpaRepository.findByIdOrNull(UUID.fromString(accountId))!!.user.id
        assertThat(response).isEqualTo("Hello, [$userId]!")
    }

    @Test
    fun hello_withoutAuthorization() {
        run(
            GET, "/api/hello",
            UNAUTHORIZED
        )
    }

    // TODO expired
    // TODO invalid

}
