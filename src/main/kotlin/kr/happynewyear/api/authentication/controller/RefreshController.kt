package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.authentication.dto.RefreshRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.service.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/refresh")
class RefreshController(
    val tokenService: TokenService
) {

    @PostMapping
    fun refresh(@RequestBody req: RefreshRequest): ResponseEntity<TokenResponse> {
        val token = tokenService.refresh(req.refreshToken)
        val res = TokenResponse.from(token)
        return ResponseEntity.ok(res)
    }

}