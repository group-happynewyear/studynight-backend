package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.StudyListResponse
import kr.happynewyear.api.studynight.dto.StudyResponse
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studyCreateRequestFixture
import kr.happynewyear.library.test.LogonApiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK

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
    // TODO not found
    // TODO not mine

}
