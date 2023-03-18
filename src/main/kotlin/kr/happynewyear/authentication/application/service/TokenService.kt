package kr.happynewyear.authentication.application.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS256
import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.application.exception.RefreshTokenNotFoundException
import kr.happynewyear.authentication.domain.model.RefreshToken
import kr.happynewyear.authentication.domain.model.User
import kr.happynewyear.authentication.domain.repository.RefreshTokenChainRepository
import kr.happynewyear.authentication.domain.repository.RefreshTokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenChainRepository: RefreshTokenChainRepository,
    @Value("\${token.access.secret}") private val secret: String,
    @Value("\${token.access.expiration-minutes}") private val expirationMinutes: Long,
    @Value("\${token.refresh.expiration-days}") private val expirationDays: Long
) {

    private val encodedSecret = Base64.getEncoder().encodeToString(secret.toByteArray())


    @Transactional
    fun issue(user: User): TokenResult {
        val refreshToken = RefreshToken.create(expirationDays, user)
        refreshTokenRepository.save(refreshToken)
        val accessToken = writeJwt(user, expirationMinutes)
        return TokenResult.of(accessToken, refreshToken)
    }


    @Transactional
    fun validate(refreshTokenId: UUID) {
        val refreshToken = refreshTokenRepository.findById(refreshTokenId) ?: throw RefreshTokenNotFoundException()
        if (refreshToken.used) refreshTokenChainRepository.delete(refreshToken.refreshTokenChain)
    }

    @Transactional
    fun refresh(refreshTokenId: UUID): TokenResult {
        val oldRefreshToken = refreshTokenRepository.findById(refreshTokenId)
        if (oldRefreshToken == null || oldRefreshToken.isExpired()) throw RefreshTokenNotFoundException()

        val newRefreshToken = oldRefreshToken.reproduce(expirationDays)
        val accessToken = writeJwt(newRefreshToken.user, expirationMinutes)
        return TokenResult.of(accessToken, newRefreshToken)
    }


    private fun writeJwt(user: User, expirationMinutes: Long): String {
        val claims = Jwts.claims()
        claims.subject = user.id.toString()
        claims.issuedAt = Date()
        claims.expiration = Date(claims.issuedAt.time + expirationMinutes * 60 * 1000)
        return Jwts.builder().signWith(HS256, encodedSecret).setClaims(claims).compact()
    }

}
