package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.StudentExistResponse
import kr.happynewyear.api.studynight.dto.StudentMyResponse
import kr.happynewyear.api.studynight.fixture.matchSourceFixture
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studentUpdateRequestFixture
import kr.happynewyear.library.test.ApiTest
import kr.happynewyear.studynight.constant.condition.Position.CLOUD
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.*
import org.springframework.http.HttpStatus.*

class StudentControllerTest : ApiTest() {

    @BeforeEach
    fun setup() {
        login()
    }


    @Test
    fun isExist_true() {
        run(POST, "/api/students", studentCreateRequestFixture(), CREATED)

        val res = call(
            GET, "/api/students/me/is_exist",
            OK, StudentExistResponse::class.java
        )

        assertThat(res.isExist).isTrue
    }

    @Test
    fun isExist_false() {
        val res = call(
            GET, "/api/students/me/is_exist",
            OK, StudentExistResponse::class.java
        )

        assertThat(res.isExist).isFalse
    }


    @Test
    fun create() {
        val req = studentCreateRequestFixture()
        val location = redirect(
            POST, "/api/students", req,
            CREATED
        )

        assertThat(location).startsWith("/api/students/")
    }

    @Test
    fun create_duplicated() {
        val req = studentCreateRequestFixture()
        run(POST, "/api/students", req, CREATED)

        run(
            POST, "/api/students", req,
            BAD_REQUEST
        )
    }


    @Test
    fun update() {
        val createReq = studentCreateRequestFixture(matchSourceFixture(SERVER))
        run(POST, "/api/students", createReq, CREATED)

        val updateReq = studentUpdateRequestFixture(matchSourceFixture(CLOUD))
        run(PUT, "/api/students/me", updateReq, NO_CONTENT)

        val res = call(GET, "/api/students/me", OK, StudentMyResponse::class.java)
        assertThat(res.condition).isEqualTo(updateReq.condition)
    }

}
