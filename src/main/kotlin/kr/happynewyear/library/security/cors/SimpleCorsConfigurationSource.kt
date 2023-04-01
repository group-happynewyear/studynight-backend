package kr.happynewyear.library.security.cors

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

class SimpleCorsConfigurationSource(
    allowedOrigin: String
) : CorsConfigurationSource {

    private val log = LoggerFactory.getLogger(javaClass)


    private val corsConfiguration = CorsConfiguration()

    init {
        corsConfiguration.addAllowedOrigin(allowedOrigin)
        corsConfiguration.addAllowedMethod("*")
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.allowCredentials = true
        corsConfiguration.maxAge = 3600L

        log.info("Origin $allowedOrigin is allowed.")
    }


    override fun getCorsConfiguration(request: HttpServletRequest): CorsConfiguration {
        return corsConfiguration
    }

}
