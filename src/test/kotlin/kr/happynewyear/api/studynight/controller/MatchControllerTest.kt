package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.MatchCreateRequest
import kr.happynewyear.api.studynight.dto.MatchResponse
import kr.happynewyear.api.studynight.fixture.matchParameterFixture
import kr.happynewyear.api.studynight.fixture.matchSourceFixture
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studyCreateRequestFixture
import kr.happynewyear.library.test.LogonApiTest
import kr.happynewyear.studynight.constant.condition.Position
import kr.happynewyear.studynight.constant.condition.Position.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK

class MatchControllerTest : LogonApiTest() {

    fun createStudent(position: Position): String {
        val req = studentCreateRequestFixture(matchSourceFixture(position))
        val location = redirect(POST, "/api/students", req, CREATED)
        return location.split("/api/students/")[1]
    }

    fun createStudy(positions: Set<Position>): String {
        val req = studyCreateRequestFixture(matchParameterFixture(positions))
        val location = redirect(POST, "/api/studies", req, CREATED)
        return location.split("/api/studies/")[1]
    }


    @Test
    fun create() {
        login()
        createStudent(DATA)

        login()
        createStudent(WEB)

        login()
        createStudent(SERVER)
        val studyId = createStudy(setOf(WEB, SERVER))

        val req = MatchCreateRequest(studyId, matchParameterFixture(setOf(WEB)))
        val location = redirect(
            POST, "/api/matches", req,
            CREATED
        )

        assertThat(location).startsWith("/api/matches/")

        // TODO then send mail
    }
    // TODO not mine

    // TODO validate out-of-bound


    @Test
    fun get() {
        login()
        createStudent(DATA)

        login()
        val webStudentId = createStudent(WEB)

        login()
        createStudent(SERVER)
        val studyId = createStudy(setOf(WEB, SERVER))
        val createReq = MatchCreateRequest(studyId, matchParameterFixture(setOf(WEB)))
        val location = redirect(POST, "/api/matches", createReq, CREATED)

        val res = call(
            GET, location,
            OK, MatchResponse::class.java
        )

        assertThat(res.invitations.size).isEqualTo(1)
        assertThat(res.invitations[0].student.id).isEqualTo(webStudentId)
    }
    // TODO not mine

}
