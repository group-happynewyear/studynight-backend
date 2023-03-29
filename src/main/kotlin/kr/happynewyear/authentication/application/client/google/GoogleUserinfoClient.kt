package kr.happynewyear.authentication.application.client.google

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "google-userinfo-client", url = "\${social-login.google.userinfo-url}")
interface GoogleUserinfoClient {

    @GetMapping
    fun exchange(@RequestHeader("Authorization") authorizationHeader: String): GoogleUserinfoResponse

}
