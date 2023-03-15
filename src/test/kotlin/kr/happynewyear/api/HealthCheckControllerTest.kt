package kr.happynewyear.api

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus.OK

class HealthCheckControllerTest : ApiTest() {

    @Value("\${spring.application.name}")
    lateinit var applicationName: String

    @Value("\${spring.profiles.active}")
    lateinit var activeProfiles: String


    @Test
    fun healthCheck() {
        val response = call(
            GET, "/api/health-check",
            OK
        )

        assertThat(response).isEqualTo("$applicationName is on $activeProfiles")
    }

}