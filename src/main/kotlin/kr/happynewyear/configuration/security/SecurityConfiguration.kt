package kr.happynewyear.configuration.security

import kr.happynewyear.library.security.authentication.AuthenticationFilter
import kr.happynewyear.library.security.cors.CorsConfigurationSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    @Value("\${security.cors.allowed-origin}") private val allowedOrigin: String,
    @Value("\${security.token.access.secret}") private val secret: String
) {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .httpBasic().disable()
            .formLogin().disable()
            .sessionManagement().disable()
            .csrf().disable()
            .cors().configurationSource(CorsConfigurationSource(allowedOrigin))

        httpSecurity
            .addFilterBefore(AuthenticationFilter(secret), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests()
            // base
            .requestMatchers(GET, "/error", "/favicon.ico").permitAll()
            .requestMatchers(GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .requestMatchers(GET, "/reports/tests/test/**", "/reports/jacoco/test/html/**").permitAll()
            .requestMatchers(GET, "/api/health-check").permitAll()
            .requestMatchers(GET, "/api/hello").authenticated()
            .requestMatchers(POST, "/api/alert").authenticated()
            // portable-mq
            .requestMatchers("/portable-mq/**").authenticated()
            .requestMatchers(GET, "/portable-mq/deadletter/redrive-token").authenticated()
            // authentication
            .requestMatchers(POST, "/api/accounts", "/api/login", "/api/refresh").permitAll()
            .requestMatchers(GET, "/api/social-login/**").permitAll()
            // studynight
            .requestMatchers(POST, "/api/students").authenticated()
            .requestMatchers(GET, "/api/students/me", "/api/students/me/is_exist").authenticated()
            .requestMatchers(PUT, "/api/students/me").authenticated()
            .requestMatchers(POST, "/api/studies").authenticated()
            .requestMatchers(GET, "/api/studies", "/api/studies/*").authenticated()
            .requestMatchers(PUT, "/api/studies/*").authenticated()
            .requestMatchers(POST, "/api/matches").authenticated()
            .requestMatchers(GET, "/api/matches/*").authenticated()
            .requestMatchers(GET, "/invitations/*/accept", "/invitations/*/reject").permitAll()
            // bff-web
            .requestMatchers(GET, "/bff-web/enum").permitAll()
            .requestMatchers(GET, "/bff-web/student/me/is_exist").authenticated()
            .requestMatchers(POST, "/bff-web/student").authenticated()
            .requestMatchers(POST, "/bff-web/study").authenticated()
            .requestMatchers(GET, "/bff-web/study/*").authenticated()
            .requestMatchers(GET, "/bff-web/study/condition/*").authenticated()
            .requestMatchers(GET, "/bff-web/study/list").authenticated()
            .requestMatchers(POST, "/bff-web/booking").authenticated()
            // -
            .anyRequest().denyAll()
            .and().exceptionHandling()
            .authenticationEntryPoint { _, response, _ -> response?.sendError(UNAUTHORIZED.value()) }
            .accessDeniedHandler { _, response, _ -> response?.sendError(FORBIDDEN.value()) }

        return httpSecurity.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { throw IllegalStateException() }
    }

}
