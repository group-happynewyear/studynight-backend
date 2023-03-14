package kr.happynewyear.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .httpBasic().disable()
            .formLogin().disable()
            .sessionManagement().disable()
            .csrf().disable()

        httpSecurity.authorizeHttpRequests()
            .requestMatchers(GET, "/api/health-check").permitAll()
            .anyRequest().authenticated()

        return httpSecurity.build()
    }

}