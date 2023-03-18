package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.RefreshTokenChain
import kr.happynewyear.authentication.domain.repository.RefreshTokenChainRepository
import org.springframework.stereotype.Repository

@Repository
class RefreshTokenChainRepositoryAdapter(
    private val refreshTokenChainJpaRepository: RefreshTokenChainJpaRepository
) : RefreshTokenChainRepository {

    override fun delete(refreshTokenChain: RefreshTokenChain) {
        refreshTokenChainJpaRepository.delete(refreshTokenChain)
    }

}
