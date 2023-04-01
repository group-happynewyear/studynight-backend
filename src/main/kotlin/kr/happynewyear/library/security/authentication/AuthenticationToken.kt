package kr.happynewyear.library.security.authentication

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class AuthenticationToken(
    private val principal: Principal,
    private val authorities: Collection<String>
) : Authentication {

    override fun getPrincipal(): Principal = principal
    override fun getAuthorities(): Collection<GrantedAuthority> = authorities.map { s -> SimpleGrantedAuthority(s) }

    override fun isAuthenticated(): Boolean = true
    override fun setAuthenticated(isAuthenticated: Boolean) {}

    override fun getName(): String? = null
    override fun getCredentials(): Any? = null
    override fun getDetails(): Any? = null

}
