package kr.happynewyear.api.authentication.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class AccountCreateRequest(

    @field:Email
    val email: String,

    @field:Size(min = 8)
    val password: String

)
