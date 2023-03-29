package kr.happynewyear.authentication.application.client

import kr.happynewyear.authentication.constant.SocialAccountProvider

interface ExternalAccount {
    val provider: SocialAccountProvider
    val id: String
}
