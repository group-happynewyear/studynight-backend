package kr.happynewyear.authentication.domain.model

import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import lombok.EqualsAndHashCode
import lombok.ToString
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*

@Entity
@Table(
    name = "refresh_tokens"
)
@EqualsAndHashCode(of = ["id"])
@ToString(of = ["id"])
class RefreshToken(
    expirationDays: Long,
    refreshTokenChain: RefreshTokenChain
) {

    @Id
    val id: UUID = UUID.randomUUID()

    @Column(
        name = "expired_at",
        nullable = false, updatable = false, unique = false
    )
    val expiredAt: LocalDateTime = now().plusDays(expirationDays)

    @Column(
        name = "used",
        nullable = false, updatable = true, unique = false
    )
    var used: Boolean = false

    @ManyToOne(
        fetch = LAZY, optional = false
    )
    @JoinColumn(
        name = "refresh_token_chain_id",
        nullable = false, updatable = false, unique = false
    )
    val refreshTokenChain: RefreshTokenChain = refreshTokenChain

}
