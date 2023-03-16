package kr.happynewyear.api.authentication.controller

import kr.happynewyear.api.authentication.dto.LoginRequest
import kr.happynewyear.api.authentication.dto.TokenResponse
import kr.happynewyear.authentication.application.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/login")
class LoginController(
    val accountService: AccountService
) {

    @PostMapping
    fun login(@RequestBody req: LoginRequest): ResponseEntity<TokenResponse> {
        val token = accountService.login(req.email, req.password)
        val res = TokenResponse.from(token)
        return ResponseEntity.ok(res)
    }

}