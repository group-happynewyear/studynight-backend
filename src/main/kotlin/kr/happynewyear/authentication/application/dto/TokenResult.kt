package kr.happynewyear.authentication.application.dto

import kr.happynewyear.authentication.domain.model.RefreshToken
import java.util.*

data class TokenResult(
    val accessToken: String,
    val refreshTokenId: UUID
) {
    companion object {
        fun of(accessToken: String, refreshToken: RefreshToken): TokenResult {
            return TokenResult(accessToken, refreshToken.id)
        }
    }
}
