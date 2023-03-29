package kr.happynewyear.configuration.client

import kr.happynewyear.StudynightBackendApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
@EnableFeignClients(basePackageClasses = [StudynightBackendApplication::class])
class ClientConfiguration {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }

}