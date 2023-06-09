package kr.happynewyear.api.authentication.controller

import jakarta.validation.Valid
import kr.happynewyear.api.authentication.dto.AccountCreateRequest
import kr.happynewyear.authentication.application.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/accounts")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping
    fun create(@Valid @RequestBody req: AccountCreateRequest): ResponseEntity<Void> {
        val account = accountService.create(req.email, req.password)
        val location = "/api/accounts/${account.id}"
        return ResponseEntity.created(URI.create(location)).build()
    }

}
