package kr.happynewyear.library.security.authentication

import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.util.*

class AuthenticationFilter(
    secret: String,
) : GenericFilterBean() {

    private val encodedSecret = Base64.getEncoder().encodeToString(secret.toByteArray())


    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        doFilter(request as HttpServletRequest, response as HttpServletResponse, chain!!)
    }

    private fun doFilter(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        try {
            authenticate(req)
        } catch (_: Exception) {
        } finally {
            chain.doFilter(req, res)
        }
    }

    private fun authenticate(req: HttpServletRequest) {
        val jwt = req.getHeader("Authorization")?.replace("Bearer ", "") ?: return

        val claims = Jwts.parser().setSigningKey(encodedSecret).parseClaimsJws(jwt).body
        val principal = Principal(UUID.fromString(claims.subject))
        val authorities = emptySet<String>()
        val authentication = AuthenticationToken(principal, authorities)

        SecurityContextHolder.getContext().authentication = authentication
    }

}
