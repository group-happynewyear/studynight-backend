package kr.happynewyear.authentication.domain.repository

import kr.happynewyear.authentication.constant.SocialAccountProvider
import kr.happynewyear.authentication.domain.model.SocialAccount

interface SocialAccountRepository {

    fun save(socialAccount: SocialAccount)
    fun existsByExternalAccount(provider: SocialAccountProvider, externalId: String): Boolean
    fun findByExternalAccount(provider: SocialAccountProvider, externalId: String): SocialAccount?

}
