package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.InvitationResponse
import kr.happynewyear.library.test.LogonApiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.PUT
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import java.util.*

class InvitationControllerTest : LogonApiTest() {

    @Test
    fun accept() {
        val invitationId = UUID.randomUUID() // TODO

        val location = redirect(
            PUT, "/api/invitations/$invitationId/accept",
            NO_CONTENT
        )

        assertThat(location).isEqualTo("/api/invitations/$invitationId")
    }
    // TODO duplicated
    // TODO not mine


    @Test
    fun get() {
        val invitationId = UUID.randomUUID() // TODO

        val response = call(
            GET, "/api/invitations/$invitationId",
            OK, InvitationResponse::class.java
        )

        assertThat(response).isNotNull
    }
    // TODO not mine

}
