package kr.happynewyear.authentication.application.service

import kr.happynewyear.authentication.application.dto.TokenResult
import kr.happynewyear.authentication.domain.model.Account
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService {

    fun create(email: String, password: String): Account {
        TODO("impl")
    }

    fun login(email: String, password: String): TokenResult {
        TODO("impl")
    }

}