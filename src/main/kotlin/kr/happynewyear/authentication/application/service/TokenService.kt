package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.application.exception.RefreshTokenNotFoundException
import kr.happynewyear.authentication.application.exception.RefreshTokenReusedException
import kr.happynewyear.authentication.domain.model.RefreshToken
import kr.happynewyear.authentication.domain.model.User
import kr.happynewyear.authentication.domain.repository.RefreshTokenChainRepository
import kr.happynewyear.authentication.domain.repository.RefreshTokenRepository
import kr.happynewyear.library.exception.AlertSender
import kr.happynewyear.library.utility.Dates
import kr.happynewyear.library.utility.JwtIO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenChainRepository: RefreshTokenChainRepository,
    private val alertSender: AlertSender,
    @Value("\${token.access.secret}") private val secret: String,
    @Value("\${token.access.expiration-minutes}") private val expirationMinutes: Long,
    @Value("\${token.refresh.expiration-days}") private val expirationDays: Long
) {

    @Transactional
    fun issue(user: User): TokenResult {
        val refreshToken = RefreshToken.create(expirationDays, user)
        refreshTokenRepository.save(refreshToken)
        val accessToken = writeJwt(user)
        return TokenResult.of(accessToken, refreshToken)
    }


    @Transactional
    fun validate(refreshTokenId: UUID) {
        val refreshToken = refreshTokenRepository.findById(refreshTokenId) ?: throw RefreshTokenNotFoundException()
        if (refreshToken.used) {
            refreshTokenChainRepository.delete(refreshToken.refreshTokenChain)
            alertSender.sendAsync(RefreshTokenReusedException())
        }
    }

    @Transactional
    fun refresh(refreshTokenId: UUID): TokenResult {
        val oldRefreshToken = refreshTokenRepository.findById(refreshTokenId)
        if (oldRefreshToken == null || oldRefreshToken.isExpired()) throw RefreshTokenNotFoundException()

        val newRefreshToken = oldRefreshToken.reproduce(expirationDays)
        val accessToken = writeJwt(newRefreshToken.user)
        return TokenResult.of(accessToken, newRefreshToken)
    }


    private fun writeJwt(user: User): String {
        val sub = user.id.toString()
        val iat = Dates.now()
        val exp = Dates.plusMinutes(iat, expirationMinutes)
        return JwtIO.write(sub, iat, exp, secret)
    }

}
