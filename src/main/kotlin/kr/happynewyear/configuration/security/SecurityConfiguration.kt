package kr.happynewyear.configuration.security

import kr.happynewyear.library.security.AuthenticationFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    @Value("\${token.access.secret}") private val secret: String
) {

    private val authenticationFilter: AuthenticationFilter = AuthenticationFilter(secret)


    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .httpBasic().disable()
            .formLogin().disable()
            .sessionManagement().disable()
            .csrf().disable()
            .userDetailsService { throw IllegalStateException() }
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        httpSecurity.authorizeHttpRequests()
            .requestMatchers(GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .requestMatchers(GET, "/error").permitAll()
            .requestMatchers(GET, "/api/health-check").permitAll()
            .requestMatchers(GET, "/api/hello").authenticated()
            .requestMatchers(POST, "/api/accounts", "/api/login", "/api/refresh").permitAll()
            .requestMatchers(GET, "/api/social-login/**").permitAll()
            .anyRequest().denyAll()
            .and().exceptionHandling()
            .authenticationEntryPoint { _, response, _ -> response?.sendError(UNAUTHORIZED.value()) }
            .accessDeniedHandler { _, response, _ -> response?.sendError(FORBIDDEN.value()) }

        return httpSecurity.build()
    }

}
