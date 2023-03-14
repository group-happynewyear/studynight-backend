package kr.happynewyear.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/health-check")
class HealthCheckController {

    @GetMapping
    fun healthCheck(): String {
        return "OK"
    }

}