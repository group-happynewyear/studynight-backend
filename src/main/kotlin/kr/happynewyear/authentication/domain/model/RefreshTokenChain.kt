package kr.happynewyear.authentication.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable

@Entity
@Table(
    name = "refresh_token_chains"
)
class RefreshTokenChain(
    user: User
) : Identifiable() {

    companion object {
        fun create(user: User): RefreshTokenChain {
            return RefreshTokenChain(user)
        }
    }


    @ManyToOne(
        fetch = LAZY, optional = false
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
    private val _refreshTokens: MutableList<RefreshToken> = mutableListOf()
    private val refreshTokens: List<RefreshToken> get() = _refreshTokens.toList() // TODO batch delete


    fun add(refreshToken: RefreshToken) {
        _refreshTokens.add(refreshToken)
    }

}
