package kr.happynewyear.authentication.domain.repository

import kr.happynewyear.authentication.domain.model.RefreshToken
import java.util.*

interface RefreshTokenRepository {

    fun save(refreshToken: RefreshToken)
    fun findById(refreshTokenId: UUID): RefreshToken?
    fun findExpired(): List<RefreshToken>

}