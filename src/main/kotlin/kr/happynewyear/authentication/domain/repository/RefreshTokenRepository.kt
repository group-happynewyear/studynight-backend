package kr.happynewyear.authentication.domain.repository

import kr.happynewyear.authentication.domain.model.RefreshToken

interface RefreshTokenRepository {

    fun save(refreshToken: RefreshToken)

}