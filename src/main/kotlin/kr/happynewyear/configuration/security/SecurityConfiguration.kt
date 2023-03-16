package kr.happynewyear.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
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
            .requestMatchers(GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .requestMatchers(GET, "/api/health-check").permitAll()
            .requestMatchers(POST, "/api/accounts", "/api/login").permitAll()
            .requestMatchers(GET, "/api/social-login/**").permitAll()
            .anyRequest().authenticated()

        return httpSecurity.build()
    }

}