package kr.happynewyear.authentication.domain.repository

import kr.happynewyear.authentication.domain.model.RefreshTokenChain

interface RefreshTokenChainRepository {

    fun delete(refreshTokenChain: RefreshTokenChain)

}
