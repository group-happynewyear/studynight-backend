package kr.happynewyear.api

import kr.happynewyear.library.exception.AlertSender
import kr.happynewyear.library.security.authentication.Authenticated
import kr.happynewyear.library.security.authentication.Principal
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DefaultController(
    private val alertSender: AlertSender,
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

    @PostMapping("/alert")
    fun alert(@Authenticated principal: Principal) {
        alertSender.sendAsync(RuntimeException("Alert Test"))
    }

}
