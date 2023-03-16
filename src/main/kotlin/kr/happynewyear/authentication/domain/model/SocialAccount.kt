package kr.happynewyear.authentication.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.authentication.constant.SocialAccountProvider
import lombok.EqualsAndHashCode
import lombok.ToString
import java.util.*

@Entity
@Table(
    name = "social_accounts",
    uniqueConstraints = [UniqueConstraint(columnNames = ["provider", "external_id"])]
)
@EqualsAndHashCode(of = ["id"])
@ToString(of = ["id"])
class SocialAccount(
    provider: SocialAccountProvider,
    externalId: String
) {

    @Id
    val id: UUID = UUID.randomUUID()

    @Enumerated(STRING)
    @Column(
        name = "provider",
        nullable = false, updatable = false, unique = false
    )
    val provider: SocialAccountProvider = provider

    @Column(
        name = "external_id",
        nullable = false, updatable = false, unique = false
    )
    val externalId: String = externalId

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
