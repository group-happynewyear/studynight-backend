package kr.happynewyear.api

import kr.happynewyear.library.security.Authenticated
import kr.happynewyear.library.security.Principal
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DefaultController(
    @Value("\${spring.application.name}") private val applicationName: String,
    @Value("\${spring.profiles.active}") private val profile: String
) {

    @GetMapping("/health-check")
    fun healthCheck(): String {
        return "[$applicationName] is on [$profile]"
    }

    @GetMapping("/hello")
    fun hello(@Authenticated principal: Principal): String {
        return "Hello, [${principal.userId}]!"
    }
    // TODO with locale

}
