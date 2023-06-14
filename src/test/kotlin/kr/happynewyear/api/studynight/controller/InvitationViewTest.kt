package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.MatchCreateRequest
import kr.happynewyear.api.studynight.dto.MatchResponse
import kr.happynewyear.api.studynight.dto.StudyResponse
import kr.happynewyear.api.studynight.fixture.matchParameterFixture
import kr.happynewyear.api.studynight.fixture.matchSourceFixture
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studyCreateRequestFixture
import kr.happynewyear.library.test.ApiTest
import kr.happynewyear.studynight.constant.condition.Position
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import kr.happynewyear.studynight.constant.condition.Position.WEB
import kr.happynewyear.studynight.type.MatchParameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK

class InvitationViewTest : ApiTest() {

    fun createStudent(position: Position): String {
        val req = studentCreateRequestFixture(matchSourceFixture(position))
        val location = redirect(POST, "/api/students", req, CREATED)
        return location.split("/api/students/")[1]
    }

    fun createStudy(matchParam: MatchParameter): String {
        val req = studyCreateRequestFixture(matchParam)
        val location = redirect(POST, "/api/studies", req, CREATED)
        return location.split("/api/studies/")[1]
    }

    fun createMatch(studyId: String, matchParam: MatchParameter): String {
        val createReq = MatchCreateRequest(studyId, matchParam, 1)
        val location = redirect(POST, "/api/matches", createReq, CREATED)
        return location.split("/api/matches/")[1]
    }


    @Test
    fun accept() {
        login()
        val webStudentId = createStudent(WEB)

        login()
        createStudent(SERVER)
        val param = matchParameterFixture(setOf(WEB))
        val studyId = createStudy(param)
        val matchId = createMatch(studyId, param)
        val matchRes = call(GET, "/api/matches/$matchId", OK, MatchResponse::class.java)
        logout()

        val invitationId = matchRes.invitations.map { it.id }[0]
        val html = call(GET, "/invitations/$invitationId/accept", OK)
        assertThat(html).contains(studyId)

        login()
        val studyRes = call(GET, "/api/studies/$studyId", OK, StudyResponse::class.java)
        assertThat(studyRes.guests.map { it.id }).contains(webStudentId)
    }
    // TODO duplicated

}
