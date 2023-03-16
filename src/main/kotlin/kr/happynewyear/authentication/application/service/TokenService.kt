package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.application.dto.TokenResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TokenService {

    fun refresh(refreshTokenId: String): TokenResult {
        TODO("impl")
    }

}