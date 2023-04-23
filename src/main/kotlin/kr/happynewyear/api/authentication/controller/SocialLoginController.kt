package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.service.SocialAccountService
import kr.happynewyear.authentication.constant.SocialAccountProvider
import org.apache.tomcat.websocket.Constants.FOUND
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/social-login")
class SocialLoginController(
    private val socialAccountService: SocialAccountService,
    @Value("\${security.cors.allowed-origin}") private val allowedOrigin: String,
) {

    @GetMapping("/page/providers/{provider}")
    fun page(@PathVariable provider: SocialAccountProvider): ResponseEntity<Void> {
        val location = socialAccountService.locatePage(provider)
        return ResponseEntity.status(FOUND).location(URI.create(location)).build()
    }

    @GetMapping("/callback/providers/{provider}")
    fun callback(
        @PathVariable provider: SocialAccountProvider,
        @RequestParam code: String
    ): ResponseEntity<Void> {
        val token = socialAccountService.login(provider, code)
        val res = TokenResponse.from(token)
        val location = "$allowedOrigin/token?access_token=${res.accessToken}&refresh_token=${res.refreshToken}"
        return ResponseEntity.status(FOUND).location(URI.create(location)).build()
    }

}
