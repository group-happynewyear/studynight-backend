package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.authentication.dto.RefreshRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.service.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/refresh")
class RefreshController(
    private val tokenService: TokenService
) {

    @PostMapping
    fun refresh(@RequestBody req: RefreshRequest): ResponseEntity<TokenResponse> {
        val refreshTokenId = UUID.fromString(req.refreshToken)
        val token = tokenService.refresh(refreshTokenId)
        val res = TokenResponse.from(token)
        return ResponseEntity.ok(res)
    }

}
