package kr.happynewyear.library.notification.slack

import kr.happynewyear.library.constant.Profiles.Companion.LIVE
import kr.happynewyear.library.constant.Profiles.Companion.STAGE
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.POST
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@Profile(STAGE, LIVE)
class HttpSlackSender(
    private val restTemplate: RestTemplate
) : SlackSender {

    override fun send(address: String, message: String) {
        val headers = HttpHeaders()
        headers.contentType = APPLICATION_JSON
        val body = mapOf(Pair("text", message))
        val httpEntity = HttpEntity<Map<String, String>>(body, headers)

        val exchange = restTemplate.exchange(address, POST, httpEntity, Void::class.java)
        if (exchange.statusCode.isError) throw RuntimeException("Fail to send slack.")
    }

}
