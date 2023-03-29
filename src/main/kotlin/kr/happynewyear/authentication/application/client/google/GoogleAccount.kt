package kr.happynewyear.authentication.application.client.google

import kr.happynewyear.authentication.application.client.ExternalAccount
import kr.happynewyear.authentication.constant.SocialAccountProvider
import kr.happynewyear.authentication.constant.SocialAccountProvider.GOOGLE

data class GoogleAccount(
    override val id: String
) : ExternalAccount {
    override val provider: SocialAccountProvider = GOOGLE
}
