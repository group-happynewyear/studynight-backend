package kr.happynewyear.authentication.infrastructure.google

import kr.happynewyear.authentication.application.client.ExternalAccount
import kr.happynewyear.authentication.application.client.ExternalAccountClient
import kr.happynewyear.authentication.constant.SocialAccountProvider.GOOGLE
import kr.happynewyear.library.constant.Profiles.Companion.LOCAL
import kr.happynewyear.library.constant.Profiles.Companion.TEST
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(TEST, LOCAL)
class StubGoogleAccountClient : ExternalAccountClient() {

    override val supportingProvider = GOOGLE


    override fun getLoginPageUrl(): String {
        return "https://stub.google.com"
    }

    override fun getLoginParameter(): Map<String, String> {
        return emptyMap()
    }

    override fun fetchExternalAccount(authorizationCode: String): ExternalAccount {
        return GoogleAccount("stub", "stub@gmail.com")
    }

}
