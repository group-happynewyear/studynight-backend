package kr.happynewyear.api.studynight.controller

import com.github.josh910830.portablemq.core.producer.PortableProducer
import com.ninjasquad.springmockk.SpykBean
import io.mockk.verify
import kr.happynewyear.api.studynight.dto.MatchCreateRequest
import kr.happynewyear.api.studynight.dto.MatchResponse
import kr.happynewyear.api.studynight.dto.StudentMyResponse
import kr.happynewyear.api.studynight.fixture.matchParameterFixture
import kr.happynewyear.api.studynight.fixture.matchSourceFixture
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studyCreateRequestFixture
import kr.happynewyear.library.test.ApiTest
import kr.happynewyear.notification.message.UserNotificationRequest
import kr.happynewyear.studynight.constant.condition.Position
import kr.happynewyear.studynight.constant.condition.Position.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK

class MatchControllerTest : ApiTest() {

    @SpykBean
    lateinit var userNotificationRequestProducer: PortableProducer<UserNotificationRequest>


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
        val meBefore = call(GET, "/api/students/me", OK, StudentMyResponse::class.java)

        val req = MatchCreateRequest(studyId, matchParameterFixture(setOf(WEB, SERVER)))
        val location = redirect(
            POST, "/api/matches", req,
            CREATED
        )

        assertThat(location).startsWith("/api/matches/")

        val meAfter = call(GET, "/api/students/me", OK, StudentMyResponse::class.java)
        assertThat(meAfter.point).isLessThan(meBefore.point)
        assertThat(meAfter.transactions.size).isGreaterThan(meBefore.transactions.size)

        run(POST, "/api/matches", req, CREATED)
        verify(exactly = 1) { userNotificationRequestProducer.produce(any()) }
    }
    // TODO not mine
    // TODO insufficient


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
