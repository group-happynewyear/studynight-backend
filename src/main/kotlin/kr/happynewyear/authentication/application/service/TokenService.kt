package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.domain.model.RefreshToken
import kr.happynewyear.authentication.domain.model.User
import kr.happynewyear.authentication.domain.repository.RefreshTokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.UUID.randomUUID

@Service
@Transactional(readOnly = true)
class TokenService(
    val refreshTokenRepository: RefreshTokenRepository,
    @Value("\${token.refresh.expiration-days}") val expirationDays: Long
) {

    @Transactional
    fun issue(user: User): TokenResult {
        val accessToken = randomUUID()
        val refreshToken = RefreshToken.create(expirationDays, user)
        refreshTokenRepository.save(refreshToken)
        return TokenResult.of(accessToken, refreshToken)
    }

    fun refresh(refreshTokenId: String): TokenResult {
        TODO("impl")
    }

}
