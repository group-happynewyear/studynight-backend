package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.constant.SocialAccountProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SocialAccountService {

    fun locatePage(provider: SocialAccountProvider): String {
        TODO("impl")
    }

    fun login(
        provider: SocialAccountProvider,
        authorizationCode: String
    ): TokenResult {
        TODO("impl")
    }

}