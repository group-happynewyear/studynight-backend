package kr.happynewyear.authentication.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.PERSIST
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
@Table(
    name = "refresh_tokens"
)
class RefreshToken(
    expirationDays: Long,
    refreshTokenChain: RefreshTokenChain
) : Identifiable() {

    companion object {
        fun create(expirationDays: Long, user: User): RefreshToken {
            val refreshTokenChain = RefreshTokenChain.create(user)
            val refreshToken = RefreshToken(expirationDays, refreshTokenChain)
            refreshTokenChain.add(refreshToken)
            return refreshToken
        }
    }

    fun reproduce(expirationDays: Long): RefreshToken {
        used = true
        val child = RefreshToken(expirationDays, refreshTokenChain)
        refreshTokenChain.add(child)
        return child
    }


    @Column(
        name = "expired_at",
        nullable = false, updatable = false, unique = false
    )
    private val expiredAt: LocalDateTime = now().plusDays(expirationDays)

    @Column(
        name = "used",
        nullable = false, updatable = true, unique = false
    )
    private var used: Boolean = false

    @ManyToOne(
        fetch = LAZY, optional = false,
        cascade = [PERSIST]
    )
    @JoinColumn(
        name = "refresh_token_chain_id",
        nullable = false, updatable = false, unique = false
    )
    private val refreshTokenChain: RefreshTokenChain = refreshTokenChain


    val user: User get() = refreshTokenChain.user

    private fun isExpired(): Boolean {
        return now().isAfter(expiredAt)
    }

}
