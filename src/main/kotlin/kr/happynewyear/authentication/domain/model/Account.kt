package kr.happynewyear.authentication.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable

@Entity
@Table(
    name = "accounts"
)
class Account(
    email: String,
    password: String
) : Identifiable() {

    companion object {
        fun create(email: String, password: String): Account {
            return Account(email, password)
        }
    }

    @Column(
        name = "email",
        nullable = false, updatable = true, unique = true
    )
    var email: String = email

    @Column(
        name = "password",
        nullable = false, updatable = true, unique = false
    )
    var password: String = password

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
