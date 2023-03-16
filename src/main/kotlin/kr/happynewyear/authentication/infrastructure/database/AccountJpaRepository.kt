package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountJpaRepository : JpaRepository<Account, UUID> {
}