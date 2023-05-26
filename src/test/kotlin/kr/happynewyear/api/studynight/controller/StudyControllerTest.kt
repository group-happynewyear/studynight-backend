package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.StudyListResponse
import kr.happynewyear.api.studynight.dto.StudyResponse
import kr.happynewyear.api.studynight.fixture.matchParameterFixture
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studyCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studyUpdateRequestFixture
import kr.happynewyear.library.test.LogonApiTest
import kr.happynewyear.studynight.constant.condition.Position.CLOUD
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.*
import org.springframework.http.HttpStatus.*

class StudyControllerTest : LogonApiTest() {

    @BeforeEach
    fun setup() {
        loginAsNewStudent()
    }

    fun loginAsNewStudent() {
        login()
        createStudent()
    }

    fun createStudent() {
        run(POST, "/api/students", studentCreateRequestFixture(), CREATED)
    }


    @Test
    fun create() {
        val req = studyCreateRequestFixture()
        val location = redirect(
            POST, "/api/studies", req,
            CREATED
        )

        assertThat(location).startsWith("/api/studies/")
    }


    @Test
    fun list() {
        run(POST, "/api/studies", studyCreateRequestFixture(), CREATED)

        loginAsNewStudent()
        run(POST, "/api/studies", studyCreateRequestFixture(), CREATED)
        run(POST, "/api/studies", studyCreateRequestFixture(), CREATED)

        val response = call(
            GET, "/api/studies",
            OK, StudyListResponse::class.java
        )

        assertThat(response.studies).hasSize(2)
    }


    @Test
    fun get() {
        val createReq = studyCreateRequestFixture()
        val location = redirect(POST, "/api/studies", createReq, CREATED)

        val res = call(
            GET, location,
            OK, StudyResponse::class.java
        )

        assertThat(res.condition).isEqualTo(createReq.condition)
    }

    @Test
    fun update() {
        val createReq = studyCreateRequestFixture(matchParameterFixture(setOf(SERVER)))
        val location = redirect(POST, "/api/studies", createReq, CREATED)

        val updateReq = studyUpdateRequestFixture(matchParameterFixture(setOf(CLOUD)))
        run(PUT, location, updateReq, NO_CONTENT)

        val res = call(GET, location, OK, StudyResponse::class.java)
        assertThat(res.condition).isEqualTo(updateReq.condition)
    }
    // TODO not mine

}
