package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.InvitationResponse
import kr.happynewyear.api.studynight.dto.MatchCreateRequest
import kr.happynewyear.api.studynight.dto.MatchResponse
import kr.happynewyear.api.studynight.fixture.matchParameterFixture
import kr.happynewyear.api.studynight.fixture.matchSourceFixture
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studyCreateRequestFixture
import kr.happynewyear.library.test.LogonApiTest
import kr.happynewyear.studynight.constant.condition.Position
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import kr.happynewyear.studynight.constant.condition.Position.WEB
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.*
import org.springframework.http.HttpStatus.*

class InvitationControllerTest : LogonApiTest() {

    fun createStudent(position: Position): String {
        val req = studentCreateRequestFixture(matchSourceFixture(position))
        val location = redirect(POST, "/api/students", req, CREATED)
        return location.split("/api/students/")[1]
    }

    fun createInvitations(positions: Set<Position>): List<String> {
        val studyId = createStudy(positions)
        val matchId = createMatch(studyId, positions)
        val matchRes = call(GET, "/api/matches/$matchId", OK, MatchResponse::class.java)
        return matchRes.invitations.map { it.id }
    }

    fun createStudy(positions: Set<Position>): String {
        val req = studyCreateRequestFixture(matchParameterFixture(positions))
        val location = redirect(POST, "/api/studies", req, CREATED)
        return location.split("/api/studies/")[1]
    }

    fun createMatch(studyId: String, positions: Set<Position>): String {
        val createReq = MatchCreateRequest(studyId, matchParameterFixture(positions))
        val location = redirect(POST, "/api/matches", createReq, CREATED)
        return location.split("/api/matches/")[1]
    }


    @Test
    fun get() {
        val webUserId = login()
        createStudent(WEB)

        login()
        createStudent(SERVER)
        val invitationId = createInvitations(setOf(WEB))[0]

        login(webUserId)
        val response = call(
            GET, "/api/invitations/$invitationId",
            OK, InvitationResponse::class.java
        )

        assertThat(response).isNotNull
    }
    // TODO not mine


    @Test
    fun confirm() {
        val webUserId = login()
        createStudent(WEB)

        login()
        createStudent(SERVER)
        val invitationId = createInvitations(setOf(WEB))[0]

        login(webUserId)
        val location = redirect(
            PUT, "/api/invitations/$invitationId?accept=true",
            NO_CONTENT
        )

        assertThat(location).isEqualTo("/api/invitations/$invitationId")
    }
    // TODO duplicated
    // TODO not mine

}
