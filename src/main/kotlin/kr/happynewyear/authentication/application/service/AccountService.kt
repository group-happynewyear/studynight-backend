package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.application.dto.AccountResult
import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.application.exception.AccountNotFoundException
import kr.happynewyear.authentication.application.exception.DuplicatedEmailException
import kr.happynewyear.authentication.application.exception.InvalidPasswordException
import kr.happynewyear.authentication.domain.model.Account
import kr.happynewyear.authentication.domain.repository.AccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService
) {

    @Transactional
    fun create(email: String, password: String): AccountResult {
        if (accountRepository.findByEmail(email) != null) throw DuplicatedEmailException()

        val encodedPassword = passwordEncoder.encode(password)
        val account = Account.create(email, encodedPassword)
        accountRepository.save(account)
        return AccountResult.from(account)
    }

    @Transactional
    fun login(email: String, password: String): TokenResult {
        val account = accountRepository.findByEmail(email) ?: throw AccountNotFoundException()
        if (!passwordEncoder.matches(password, account.password)) throw InvalidPasswordException()
        return tokenService.issue(account.user)
    }

}
