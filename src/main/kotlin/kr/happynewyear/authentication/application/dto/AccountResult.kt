package kr.happynewyear.authentication.application.dto

import kr.happynewyear.authentication.domain.model.Account

data class AccountResult(
    val id: String
) {
    companion object {
        fun from(account: Account): AccountResult {
            return AccountResult(account.id.toString())
        }
    }
}
