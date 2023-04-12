package kr.happynewyear.api.studynight.controller


import kr.happynewyear.api.studynight.dto.MatchCreateRequest
import kr.happynewyear.api.studynight.dto.MatchResponse
import kr.happynewyear.api.studynight.fixture.matchParameterFixture
import kr.happynewyear.library.test.LogonApiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import java.util.*

class MatchControllerTest : LogonApiTest() {

    @Test
    fun create() {
        val studyId = UUID.randomUUID().toString() // TODO

        val req = MatchCreateRequest(studyId, matchParameterFixture())
        val location = redirect(
            POST, "/api/matches", req,
            CREATED
        )

        assertThat(location).startsWith("/api/matches/")
    }
    // TODO not mine


    @Test
    fun get() {
        val matchId = UUID.randomUUID() // TODO

        val response = call(
            GET, "/api/matches/$matchId",
            OK, MatchResponse::class.java
        )

        assertThat(response.invitations.size).isEqualTo(2)
    }
    // TODO not mine

}
