package kr.happynewyear.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/health-check")
class HealthCheckController {

    @Value("\${spring.application.name}")
    lateinit var applicationName: String

    @Value("\${spring.profiles.active}")
    lateinit var profile: String


    @GetMapping
    fun healthCheck(): String {
        return "$applicationName is on $profile"
    }

}