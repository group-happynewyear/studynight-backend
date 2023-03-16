package kr.happynewyear.api.authentication.dto

import kr.happynewyear.authentication.application.dto.TokenResult

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun from(token: TokenResult): TokenResponse {
            return TokenResponse(token.accessToken, token.refreshToken)
        }
    }
}
