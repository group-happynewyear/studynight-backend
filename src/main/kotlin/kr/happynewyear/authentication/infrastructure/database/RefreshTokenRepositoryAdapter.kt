package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.RefreshToken
import kr.happynewyear.authentication.domain.repository.RefreshTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime.now
import java.util.*

@Repository
class RefreshTokenRepositoryAdapter(
    private val refreshTokenJpaRepository: RefreshTokenJpaRepository
) : RefreshTokenRepository {

    override fun save(refreshToken: RefreshToken) {
        refreshTokenJpaRepository.save(refreshToken)
    }

    override fun findById(refreshTokenId: UUID): RefreshToken? {
        return refreshTokenJpaRepository.findByIdOrNull(refreshTokenId)
    }

    override fun findExpired(): List<RefreshToken> {
        return refreshTokenJpaRepository.findByExpiredAtBefore(now())
    }

}
