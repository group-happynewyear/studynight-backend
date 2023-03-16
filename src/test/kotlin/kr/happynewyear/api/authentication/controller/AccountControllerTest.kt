package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.ApiTest
import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import kr.happynewyear.authentication.application.service.AccountService
import kr.happynewyear.authentication.domain.model.Account
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED

class AccountControllerTest : ApiTest() {

    @MockBean
    lateinit var accountService: AccountService


    @Test
    fun create() {
        val email = "email@eamil.com"
        val password = "password"

        given(accountService.create(email, password))
            .willReturn(Account())

        val request = AccountCreateRequest(email, password)
        val redirect = redirect(
            POST, "/api/accounts", request,
            CREATED
        )

        assertThat(redirect).isNotNull
    }

}