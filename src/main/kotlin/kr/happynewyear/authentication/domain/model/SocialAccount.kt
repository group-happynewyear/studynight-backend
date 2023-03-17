package kr.happynewyear.authentication.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.authentication.constant.SocialAccountProvider
import kr.happynewyear.library.entity.Identifiable
import java.util.*

@Entity
@Table(
    name = "social_accounts",
    uniqueConstraints = [UniqueConstraint(columnNames = ["provider", "external_id"])]
)
class SocialAccount(
    provider: SocialAccountProvider,
    externalId: String
) : Identifiable() {

    companion object {
        fun create(provider: SocialAccountProvider, externalId: String): SocialAccount {
            return SocialAccount(provider, externalId)
        }
    }


    @Enumerated(STRING)
    @Column(
        name = "provider",
        nullable = false, updatable = false, unique = false
    )
    private val provider: SocialAccountProvider = provider

    @Column(
        name = "external_id",
        nullable = false, updatable = false, unique = false
    )
    private val externalId: String = externalId

    @OneToOne(
        fetch = LAZY, optional = false,
        cascade = [ALL], orphanRemoval = true
    )
    @JoinColumn(
        name = "user_id",
        nullable = false, updatable = false, unique = true
    )
    val user: User = User()

}
