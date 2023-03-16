package kr.happynewyear.api.authentication.dto

data class AccountCreateRequest(
    val email: String,
    val password: String
)
