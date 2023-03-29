package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.constant.SocialAccountProvider
import kr.happynewyear.authentication.domain.model.SocialAccount
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SocialAccountJpaRepository : JpaRepository<SocialAccount, UUID> {

    fun existsByProviderAndExternalId(provider: SocialAccountProvider, externalId: String): Boolean
    fun findByProviderAndExternalId(provider: SocialAccountProvider, externalId: String): SocialAccount?

}
