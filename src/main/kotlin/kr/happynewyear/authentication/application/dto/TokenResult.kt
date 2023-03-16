package kr.happynewyear.authentication.application.dto

import kr.happynewyear.authentication.domain.model.RefreshToken
import java.util.*

data class TokenResult(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(accessToken: UUID, refreshToken: RefreshToken): TokenResult {
            return TokenResult(accessToken.toString(), refreshToken.id.toString())
        }
    }
}
