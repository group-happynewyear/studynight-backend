package kr.happynewyear.authentication.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable

@Entity
@Table(
    name = "users"
)
class User(
    email: String
) : Identifiable() {

    companion object {
        fun create(email: String): User {
            return User(email)
        }
    }

    @Column(
        name = "email",
        nullable = false, updatable = true, unique = false
    )
    var email: String = email
        protected set

}
