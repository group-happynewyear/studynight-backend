package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.RefreshToken
import kr.happynewyear.authentication.domain.repository.RefreshTokenRepository
import org.springframework.stereotype.Repository

@Repository
class RefreshTokenRepositoryAdapter(
    private val refreshTokenJpaRepository: RefreshTokenJpaRepository
) : RefreshTokenRepository {

    override fun save(refreshToken: RefreshToken) {
        refreshTokenJpaRepository.save(refreshToken)
    }

}