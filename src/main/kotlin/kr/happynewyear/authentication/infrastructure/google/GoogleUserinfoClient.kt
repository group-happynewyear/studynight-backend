package kr.happynewyear.authentication.infrastructure.google

import kr.happynewyear.library.constant.Profiles.Companion.LIVE
import kr.happynewyear.library.constant.Profiles.Companion.STAGE
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@Profile(STAGE, LIVE)
@FeignClient(name = "google-userinfo-client", url = "\${social-login.google.userinfo-url}")
interface GoogleUserinfoClient {

    @GetMapping
    fun exchange(@RequestHeader("Authorization") authorizationHeader: String): GoogleUserinfoResponse

}
