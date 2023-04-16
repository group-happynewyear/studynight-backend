package kr.happynewyear.authentication.infrastructure.google

import kr.happynewyear.authentication.application.client.ExternalAccount
import kr.happynewyear.authentication.application.client.ExternalAccountClient
import kr.happynewyear.authentication.constant.SocialAccountProvider.GOOGLE
import kr.happynewyear.library.constant.Profiles.Companion.LIVE
import kr.happynewyear.library.constant.Profiles.Companion.STAGE
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(STAGE, LIVE)
class GoogleAccountClientAdapter(
    private val tokenClient: GoogleTokenClient,
    private val userinfoClient: GoogleUserinfoClient,
    @Value("\${social-login.google.login-page}") private val loginPage: String,
    @Value("\${social-login.google.client.client-id}") private val clientId: String,
    @Value("\${social-login.google.client.client-secret}") private val clientSecret: String,
    @Value("\${social-login.google.client.scope}") private val scope: String,
    @Value("\${social-login.google.client.redirect-uri}") private val redirectUri: String
) : ExternalAccountClient() {

    override val supportingProvider = GOOGLE


    override fun getLoginPageUrl(): String {
        return loginPage
    }

    override fun getLoginParameter(): Map<String, String> {
        return mapOf(
            Pair("response_type", "code"),
            Pair("client_id", clientId),
            Pair("scope", scope),
            Pair("redirect_uri", redirectUri)
        )
    }

    override fun fetchExternalAccount(authorizationCode: String): ExternalAccount {
        val tokenReq = GoogleTokenRequest(
            authorizationCode,
            clientId, clientSecret, scope, redirectUri
        )
        val tokenRes = tokenClient.exchange(tokenReq)

        val authorizationHeader = "Bearer ${tokenRes.access_token}"
        val userinfo = userinfoClient.exchange(authorizationHeader)
        return GoogleAccount(userinfo.id, userinfo.email)
    }

}
