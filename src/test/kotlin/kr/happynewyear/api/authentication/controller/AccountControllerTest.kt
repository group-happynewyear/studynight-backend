package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.ApiTest
import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED

class AccountControllerTest : ApiTest() {

    @Test
    fun create() {
        val email = "email@eamil.com"
        val password = "password"

        val request = AccountCreateRequest(email, password)
        val redirect = redirect(
            POST, "/api/accounts", request,
            CREATED
        )

        assertThat(redirect).isNotNull
    }

    // TODO email duplicated

}
