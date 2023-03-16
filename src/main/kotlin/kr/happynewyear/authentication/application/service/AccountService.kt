package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.application.dto.AccountResult
import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.application.exception.AccountNotFoundException
import kr.happynewyear.authentication.domain.model.Account
import kr.happynewyear.authentication.domain.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
    val accountRepository: AccountRepository,
    val tokenService: TokenService
) {

    @Transactional
    fun create(email: String, password: String): AccountResult {
        val account = Account.create(email, password)
        accountRepository.save(account)
        return AccountResult.from(account)
    }

    @Transactional
    fun login(email: String, password: String): TokenResult {
        val account = accountRepository.findByEmail(email) ?: throw AccountNotFoundException()
        return tokenService.issue(account.user)
    }

}
