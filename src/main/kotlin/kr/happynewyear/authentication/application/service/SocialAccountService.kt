package kr.happynewyear.authentication.application.service

import com.github.josh910830.portablemq.core.producer.PortableProducer
import kr.happynewyear.authentication.application.client.ExternalAccount
import kr.happynewyear.authentication.application.client.ExternalAccountClient
import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.constant.SocialAccountProvider
import kr.happynewyear.authentication.domain.model.SocialAccount
import kr.happynewyear.authentication.domain.repository.SocialAccountRepository
import kr.happynewyear.notification.message.ChannelCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors.toMap

@Service
@Transactional(readOnly = true)
class SocialAccountService(
    private val socialAccountRepository: SocialAccountRepository,
    private val tokenService: TokenService,
    private val channelCreateRequestProducer: PortableProducer<ChannelCreateRequest>,
    externalAccountClients: List<ExternalAccountClient>
) {

    private val resolver: Map<SocialAccountProvider, ExternalAccountClient>

    init {
        resolver = externalAccountClients.stream().collect(toMap({ it.supportingProvider }, { it }))
    }


    fun locatePage(provider: SocialAccountProvider): String {
        val client = resolver[provider]!!
        return client.locatePage()
    }


    @Transactional
    fun login(
        provider: SocialAccountProvider,
        authorizationCode: String
    ): TokenResult {
        val client = resolver[provider]!!
        val externalAccount = client.fetchExternalAccount(authorizationCode)

        createIfAbsent(externalAccount)

        val socialAccount = socialAccountRepository.findByExternalAccount(provider, externalAccount.id)!!
        return tokenService.issue(socialAccount.user)
    }

    private fun createIfAbsent(externalAccount: ExternalAccount) {
        if (socialAccountRepository.existsByExternalAccount(externalAccount.provider, externalAccount.id)) return

        val socialAccount = SocialAccount.create(externalAccount.provider, externalAccount.id, externalAccount.email)
        socialAccountRepository.save(socialAccount)

        with(socialAccount.user) {
            val message = ChannelCreateRequest.ofUserMail(id, email)
            channelCreateRequestProducer.produce(message)
        }
    }

}
