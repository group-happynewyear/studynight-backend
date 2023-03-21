package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.library.test.LogonApiTest
import kr.happynewyear.studynight.application.dto.StudyResult
import kr.happynewyear.studynight.application.service.StudyService
import kr.happynewyear.studynight.constant.ContactType.MAIL
import kr.happynewyear.studynight.constant.Experience.JUNIOR
import kr.happynewyear.studynight.constant.Intensity.HARD
import kr.happynewyear.studynight.constant.Position.SERVER
import kr.happynewyear.studynight.constant.Region.GANGNAM
import kr.happynewyear.studynight.constant.Scale.M
import kr.happynewyear.studynight.constant.Schedule.WEEKEND_AFTERNOON
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import java.util.*

class StudyControllerTest : LogonApiTest() {

    @MockBean
    lateinit var studyService: StudyService


    @Test
    fun crete() {
        given(studyService.create()).willReturn(StudyResult(UUID.randomUUID()))

        val title = "title"
        val description = "description"
        val contactType = MAIL
        val contactAddress = "email@email.com"
        val schedule = WEEKEND_AFTERNOON
        val region = GANGNAM
        val experiences = setOf(JUNIOR)
        val positions = setOf(SERVER)
        val intensity = HARD
        val scale = M

        val request = StudyCreateRequest(
            title, description,
            contactType, contactAddress,
            schedule, region, experiences, positions, intensity, scale
        )
        val location = redirect(
            POST, "/api/studies", request,
            CREATED
        )

        assertThat(location).startsWith("/api/studies/")
    }

}
