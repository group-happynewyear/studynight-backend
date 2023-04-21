package kr.happynewyear.authentication.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.happynewyear.authentication.domain.event.UserCreatedEvent
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.library.event.DomainEventPublishers

@Entity
@Table(
    name = "users"
)
class User(
    email: String
) : Identifiable() {

    companion object {
        fun create(email: String): User {
            val user = User(email)
            DomainEventPublishers.publish(UserCreatedEvent(user))
            return user
        }
    }

    @Column(
        name = "email",
        nullable = false, updatable = true, unique = false
    )
    var email: String = email
        protected set

}
