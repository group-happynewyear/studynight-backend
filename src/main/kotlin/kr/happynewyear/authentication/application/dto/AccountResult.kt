package kr.happynewyear.authentication.application.dto

import kr.happynewyear.authentication.domain.model.Account
import java.util.*

data class AccountResult(
    val id: UUID
) {
    companion object {
        fun from(account: Account): AccountResult {
            return AccountResult(account.id)
        }
    }
}
