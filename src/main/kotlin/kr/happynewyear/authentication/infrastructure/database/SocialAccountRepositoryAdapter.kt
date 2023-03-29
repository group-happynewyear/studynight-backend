package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.constant.SocialAccountProvider
import kr.happynewyear.authentication.domain.model.SocialAccount
import kr.happynewyear.authentication.domain.repository.SocialAccountRepository
import org.springframework.stereotype.Repository

@Repository
class SocialAccountRepositoryAdapter(
    private val socialAccountJpaRepository: SocialAccountJpaRepository
) : SocialAccountRepository {

    override fun save(socialAccount: SocialAccount) {
        socialAccountJpaRepository.save(socialAccount)
    }

    override fun existsByExternalAccount(provider: SocialAccountProvider, externalId: String): Boolean {
        return socialAccountJpaRepository.existsByProviderAndExternalId(provider, externalId)
    }

    override fun findByExternalAccount(provider: SocialAccountProvider, externalId: String): SocialAccount? {
        return socialAccountJpaRepository.findByProviderAndExternalId(provider, externalId)
    }

}
