package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.Account
import kr.happynewyear.authentication.domain.repository.AccountRepository
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryAdapter(
    val accountJpaRepository: AccountJpaRepository
) : AccountRepository {

    override fun save(account: Account) {
        accountJpaRepository.save(account)
    }

}