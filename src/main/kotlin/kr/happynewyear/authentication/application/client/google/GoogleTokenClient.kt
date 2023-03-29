package kr.happynewyear.authentication.application.client.google

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "google-token-client", url = "\${social-login.google.token-url}")
interface GoogleTokenClient {

    @PostMapping(
        produces = [APPLICATION_FORM_URLENCODED_VALUE],
        consumes = [APPLICATION_JSON_VALUE]
    )
    fun exchange(@RequestBody request: GoogleTokenRequest): GoogleTokenResponse

}
