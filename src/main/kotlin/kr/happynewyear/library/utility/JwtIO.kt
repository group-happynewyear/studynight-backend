package kr.happynewyear.library.utility

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS256
import java.util.*

class JwtIO {
    companion object {

        fun write(sub: String, iat: Date, exp: Date, secret: String): String {
            val claims = Jwts.claims()
            claims.subject = sub
            claims.issuedAt = iat
            claims.expiration = exp
            return Jwts.builder().signWith(HS256, encode(secret)).setClaims(claims).compact()
        }

        fun read(jwt: String, secret: String): Claims {
            return Jwts.parser().setSigningKey(encode(secret)).parseClaimsJws(jwt).body
        }

        private fun encode(raw: String): String {
            return Base64.getEncoder().encodeToString(raw.toByteArray())
        }

    }
}
