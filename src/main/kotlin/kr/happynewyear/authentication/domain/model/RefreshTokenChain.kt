package kr.happynewyear.authentication.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import lombok.EqualsAndHashCode
import lombok.ToString
import java.util.*

@Entity
@Table(
    name = "refresh_token_chains"
)
@EqualsAndHashCode(of = ["id"])
@ToString(of = ["id"])
class RefreshTokenChain(
    user: User
) {

    @Id
    val id: UUID = UUID.randomUUID()

    @OneToOne(
        fetch = FetchType.LAZY, optional = false
    )
    @JoinColumn(
        name = "user_id",
        nullable = false, updatable = false, unique = false
    )
    val user: User = user

    @OneToMany(
        mappedBy = "refreshTokenChain",
        cascade = [ALL], orphanRemoval = true
    )
    private val mutableRefreshTokens: MutableList<RefreshToken> = mutableListOf()
    val refreshTokens: List<RefreshToken> get() = mutableRefreshTokens.toList()

}
