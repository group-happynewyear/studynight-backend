package kr.happynewyear.api.studynight.controller


import kr.happynewyear.api.studynight.dto.MatchCreateRequest
import kr.happynewyear.api.studynight.dto.MatchResponse
import kr.happynewyear.library.test.LogonApiTest
import kr.happynewyear.studynight.application.dto.MatchResult
import kr.happynewyear.studynight.application.service.MatchService
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
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import java.util.*

class MatchControllerTest : LogonApiTest() {

    @MockBean
    lateinit var matchService: MatchService


    @Test
    fun create() {
        given(matchService.create()).willReturn(MatchResult(UUID.randomUUID(), 1))

        val studyId = UUID.randomUUID().toString()
        val schedule = WEEKEND_AFTERNOON
        val region = GANGNAM
        val experiences = setOf(JUNIOR)
        val positions = setOf(SERVER)
        val intensity = HARD
        val scale = M

        val request = MatchCreateRequest(
            studyId, 1,
            schedule, region, experiences, positions, intensity, scale
        )
        val location = redirect(
            POST, "/api/matches", request,
            CREATED
        )

        assertThat(location).startsWith("/api/matches/")
    }


    @Test
    fun get() {
        val matchId = UUID.randomUUID()
        given(matchService.get(matchId)).willReturn(MatchResult(matchId, 1))

        val response = call(
            GET, "/api/matches/$matchId",
            OK, MatchResponse::class.java
        )

        assertThat(response.count).isNotNull
    }
    // TODO not mine

}
