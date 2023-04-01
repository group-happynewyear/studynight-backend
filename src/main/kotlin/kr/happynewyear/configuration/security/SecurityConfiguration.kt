package kr.happynewyear.configuration.security

import jakarta.servlet.Filter
import kr.happynewyear.library.security.authentication.AuthenticationFilter
import kr.happynewyear.library.security.cors.SimpleCorsConfigurationSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    @Value("\${token.access.secret}") private val secret: String,
    @Value("\${cors.allowed-origin}") private val allowedOrigin: String
) {

    private val authenticationFilter: Filter = AuthenticationFilter(secret)
    private val corsConfigurationSource: CorsConfigurationSource = SimpleCorsConfigurationSource(allowedOrigin)


    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .httpBasic().disable()
            .formLogin().disable()
            .sessionManagement().disable()
            .csrf().disable()
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .cors().configurationSource(corsConfigurationSource)

        httpSecurity.authorizeHttpRequests()
            .requestMatchers(GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .requestMatchers(GET, "/error").permitAll()
            .requestMatchers(GET, "/api/health-check").permitAll()
            .requestMatchers(GET, "/api/hello").authenticated()
            .requestMatchers(POST, "/api/alert").authenticated()
            .requestMatchers(POST, "/api/accounts", "/api/login", "/api/refresh").permitAll()
            .requestMatchers(GET, "/api/social-login/**").permitAll()
            .requestMatchers(POST, "/api/students").authenticated()
            .requestMatchers(POST, "/api/studies").authenticated()
            .requestMatchers(GET, "/api/studies", "/api/studies/*").authenticated()
            .requestMatchers(POST, "/api/matches").authenticated()
            .requestMatchers(GET, "/api/matches/*").authenticated()
            .requestMatchers(GET, "/api/invitations/accept").permitAll() // TODO PUT and authenticated
            .requestMatchers(GET, "/api/invitations/*").permitAll() // TODO authenticated
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
