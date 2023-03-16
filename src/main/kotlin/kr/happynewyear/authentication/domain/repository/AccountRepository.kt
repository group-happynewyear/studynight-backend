package kr.happynewyear.authentication.domain.repository

import kr.happynewyear.authentication.domain.model.Account

interface AccountRepository {

    fun save(account: Account)
    fun findByEmail(email: String): Account?

}
