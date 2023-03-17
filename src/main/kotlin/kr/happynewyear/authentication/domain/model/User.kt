package kr.happynewyear.authentication.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable

@Entity
@Table(
    name = "users"
)
class User(
) : Identifiable() {

    companion object {
        fun create(): User {
            return User()
        }
    }

}
