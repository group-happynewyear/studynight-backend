package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.application.exception.RefreshTokenNotFoundException
import kr.happynewyear.authentication.domain.model.RefreshToken
import kr.happynewyear.authentication.domain.model.User
import kr.happynewyear.authentication.domain.repository.RefreshTokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    @Value("\${token.refresh.expiration-days}") private val expirationDays: Long
) {

    @Transactional
    fun issue(user: User): TokenResult {
        val accessToken = "jwt" // TODO jwt
        val refreshToken = RefreshToken.create(expirationDays, user)
        refreshTokenRepository.save(refreshToken)
        return TokenResult.of(accessToken, refreshToken)
    }

    @Transactional
    fun refresh(refreshTokenId: UUID): TokenResult {
        val accessToken = "jwt" // TODO jwt
        val oldRefreshToken = refreshTokenRepository.findById(refreshTokenId) ?: throw RefreshTokenNotFoundException()
        val newRefreshToken = oldRefreshToken.reproduce(expirationDays)
        return TokenResult.of(accessToken, newRefreshToken)
    }

}
