package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.StudentExistResponse
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.library.test.LogonApiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.*

class StudentControllerTest : LogonApiTest() {

    @BeforeEach
    fun setup() {
        login()
    }


    @Test
    fun isExist_true() {
        run(POST, "/api/students", studentCreateRequestFixture(), CREATED)

        val res = call(
            GET, "/api/students/me/is_exists",
            OK, StudentExistResponse::class.java
        )

        assertThat(res.isExist).isTrue
    }

    @Test
    fun isExist_false() {
        val res = call(
            GET, "/api/students/me/is_exists",
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

}
