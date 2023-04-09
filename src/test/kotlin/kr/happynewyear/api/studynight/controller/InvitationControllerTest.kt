package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.InvitationResponse
import kr.happynewyear.library.test.LogonApiTest
import kr.happynewyear.studynight.application.dto.InvitationResult
import kr.happynewyear.studynight.application.service.InvitationService
import kr.happynewyear.studynight.constant.ContactType.MAIL
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.PUT
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import java.util.*

class InvitationControllerTest : LogonApiTest() {

    @MockBean
    lateinit var invitationService: InvitationService


    @Test
    fun accept() {
        val invitationId = UUID.randomUUID()

        given(invitationService.accept(invitationId)).will { }

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
        val invitationId = UUID.randomUUID()

        given(invitationService.get(invitationId))
            .willReturn(InvitationResult(MAIL, "email@email.com"))

        val response = call(
            GET, "/api/invitations/$invitationId",
            OK, InvitationResponse::class.java
        )

        assertThat(response).isNotNull
    }
    // TODO not mine

}
