package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.library.test.LogonApiTest
import kr.happynewyear.studynight.application.dto.StudentResult
import kr.happynewyear.studynight.application.service.StudentService
import kr.happynewyear.studynight.constant.Experience.JUNIOR
import kr.happynewyear.studynight.constant.Intensity.HARD
import kr.happynewyear.studynight.constant.Position.SERVER
import kr.happynewyear.studynight.constant.Region.GANGNAM
import kr.happynewyear.studynight.constant.Region.ONLINE
import kr.happynewyear.studynight.constant.Schedule.WEEKEND_AFTERNOON
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import java.util.*

class StudentControllerTest : LogonApiTest() {

    @MockBean
    lateinit var studentService: StudentService


    @Test
    fun crete() {
        given(studentService.create()).willReturn(StudentResult(UUID.randomUUID()))

        val nickname = "nick"
        val schedules = setOf(WEEKEND_AFTERNOON)
        val regions = setOf(ONLINE, GANGNAM)
        val experience = JUNIOR
        val position = SERVER
        val intensity = HARD
        val scale = null

        val request = StudentCreateRequest(nickname, schedules, regions, experience, position, intensity, scale)
        val location = redirect(
            POST, "/api/students", request,
            CREATED
        )

        assertThat(location).startsWith("/api/students/")
    }

    // TODO duplicate

}
