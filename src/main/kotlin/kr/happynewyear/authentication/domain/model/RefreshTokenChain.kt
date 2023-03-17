package kr.happynewyear.authentication.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
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


    @OneToOne(
        fetch = FetchType.LAZY, optional = false
    )
    @JoinColumn(
        name = "user_id",
        nullable = false, updatable = false, unique = false
    )
    private val user: User = user

    @OneToMany(
        mappedBy = "refreshTokenChain",
        cascade = [ALL], orphanRemoval = true
    )
    private val _refreshTokens: MutableList<RefreshToken> = mutableListOf()
    private val refreshTokens: List<RefreshToken> get() = _refreshTokens.toList()


    fun add(refreshToken: RefreshToken) {
        _refreshTokens.add(refreshToken)
    }

}
