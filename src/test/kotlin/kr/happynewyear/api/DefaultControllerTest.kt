package kr.happynewyear.api

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus.OK

class DefaultControllerTest(
    @Value("\${spring.application.name}") private val applicationName: String,
    @Value("\${spring.profiles.active}") private val activeProfiles: String
) : ApiTest() {


    @Test
    fun healthCheck() {
        val response = call(
            GET, "/api/health-check",
            OK
        )

        assertThat(response).isEqualTo("[$applicationName] is on [$activeProfiles]")
    }

}
