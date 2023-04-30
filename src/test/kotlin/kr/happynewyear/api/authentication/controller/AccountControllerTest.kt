package kr.happynewyear.api.authentication.controller

import com.ninjasquad.springmockk.SpykBean
import io.mockk.verify
import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import kr.happynewyear.authentication.infrastructure.database.AccountJpaRepository
import kr.happynewyear.library.test.ApiTest
import kr.happynewyear.authentication.application.producer.UserMailChannelCreateRequestProducer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import java.util.*

class AccountControllerTest : ApiTest() {

    @Autowired
    lateinit var accountJpaRepository: AccountJpaRepository

    @SpykBean
    lateinit var userMailChannelCreateRequestProducer: UserMailChannelCreateRequestProducer


    @Test
    fun create() {
        val email = "email@eamil.com"
        val password = "password"

        val request = AccountCreateRequest(email, password)
        val location = redirect(
            POST, "/api/accounts", request,
            CREATED
        )

        val accountId = UUID.fromString(location.replace("/api/accounts/", ""))
        val account = accountJpaRepository.findByIdOrNull(accountId)
        assertThat(account!!.password).isNotEqualTo(password)
        verify { userMailChannelCreateRequestProducer.produce(any()) }
    }

    @Test
    fun create_invalidParameter() {
        val email = "notEmail"
        val password = "short"

        run(
            POST, "/api/accounts", AccountCreateRequest(email, password),
            BAD_REQUEST
        )
    }

    @Test
    fun create_duplicatedEmail() {
        val request = AccountCreateRequest("email@eamil.com", "password")
        run(POST, "/api/accounts", request, CREATED)

        run(
            POST, "/api/accounts", request,
            BAD_REQUEST
        )
    }

}
