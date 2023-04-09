package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.StudyListResponse
import kr.happynewyear.api.studynight.dto.StudyResponse
import kr.happynewyear.api.studynight.fixture.studentCreateRequestFixture
import kr.happynewyear.api.studynight.fixture.studyCreateRequestFixture
import kr.happynewyear.library.test.LogonApiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK

class StudyControllerTest : LogonApiTest() {

    @Test
    fun create() {
        run(POST, "/api/students", studentCreateRequestFixture(), CREATED)

        val req = studyCreateRequestFixture()
        val location = redirect(
            POST, "/api/studies", req,
            CREATED
        )

        assertThat(location).startsWith("/api/studies/")
    }


    @Test
    fun list() {
        val response = call(
            GET, "/api/studies",
            OK, StudyListResponse::class.java
        )

        // TODO filter mine
        assertThat(response.studies).isNotEmpty
    }


    @Test
    fun get() {
        val studyId = ""
        val response = call(
            GET, "/api/studies/$studyId",
            OK, StudyResponse::class.java
        )

        assertThat(response).isNotNull
    }
    // TODO not found
    // TODO not mine

}
