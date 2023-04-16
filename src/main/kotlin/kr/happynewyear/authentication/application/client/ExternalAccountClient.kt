package kr.happynewyear.authentication.application.client

import kr.happynewyear.authentication.constant.SocialAccountProvider
import org.springframework.web.util.UriComponentsBuilder

abstract class ExternalAccountClient {

    abstract val supportingProvider: SocialAccountProvider


    fun locatePage(): String {
        val builder = UriComponentsBuilder.fromUriString(getLoginPageUrl())
        getLoginParameter().forEach { builder.queryParam(it.key, it.value) }
        return builder.build().encode().toUriString()
    }

    protected abstract fun getLoginPageUrl(): String
    protected abstract fun getLoginParameter(): Map<String, String>


    abstract fun fetchExternalAccount(authorizationCode: String): ExternalAccount

}
