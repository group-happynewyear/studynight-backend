package kr.happynewyear.library.security.authentication

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.happynewyear.library.marshalling.jwt.JwtMarshallers
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.util.*

class AuthenticationFilter(
    private val secret: String
) : GenericFilterBean() {

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

        val claims = JwtMarshallers.read(jwt, secret)
        val principal = Principal(UUID.fromString(claims.subject))
        val authorities = emptySet<String>()
        val authentication = AuthenticationToken(principal, authorities)

        SecurityContextHolder.getContext().authentication = authentication
    }

}
