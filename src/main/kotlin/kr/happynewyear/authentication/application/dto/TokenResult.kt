package kr.happynewyear.authentication.application.dto

data class TokenResult(
    val accessToken: String,
    val refreshToken: String
)
