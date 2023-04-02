package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.api.studynight.dto.StudyListResponse
import kr.happynewyear.api.studynight.dto.StudyResponse
import kr.happynewyear.library.test.LogonApiTest
import kr.happynewyear.studynight.application.dto.StudyResult
import kr.happynewyear.studynight.application.service.StudyService
import kr.happynewyear.studynight.constant.ContactType.MAIL
import kr.happynewyear.studynight.constant.condition.Experience.JUNIOR
import kr.happynewyear.studynight.constant.condition.Intensity.HARD
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import kr.happynewyear.studynight.constant.condition.Region.GANGNAM
import kr.happynewyear.studynight.constant.condition.Scale.M
import kr.happynewyear.studynight.constant.condition.Schedule.WEEKEND_AFTERNOON
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import java.util.*

class StudyControllerTest : LogonApiTest() {

    @MockBean
    lateinit var studyService: StudyService


    @Test
    fun create() {
        given(studyService.create()).willReturn(defaultStudyResult())

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


    @Test
    fun list() {
        given(studyService.list(userId)).willReturn(listOf(defaultStudyResult()))

        val response = call(
            GET, "/api/studies",
            OK, StudyListResponse::class.java
        )

        // TODO filter mine
        assertThat(response.studies).isNotEmpty
    }


    @Test
    fun get() {
        val studyResult = defaultStudyResult()
        val studyId = studyResult.id
        given(studyService.get(studyId)).willReturn(studyResult)

        val response = call(
            GET, "/api/studies/$studyId",
            OK, StudyResponse::class.java
        )

        assertThat(response).isNotNull
    }
    // TODO not found
    // TODO not mine


    private fun defaultStudyResult(): StudyResult {
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
        return StudyResult(
            UUID.randomUUID(),
            title, description,
            contactType, contactAddress,
            schedule, region, experiences, positions, intensity, scale
        )
    }

}
