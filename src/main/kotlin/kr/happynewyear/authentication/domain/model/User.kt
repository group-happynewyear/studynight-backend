package kr.happynewyear.authentication.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.EqualsAndHashCode
import lombok.ToString
import java.util.*

@Entity
@Table(
    name = "users"
)
@EqualsAndHashCode(of = ["id"])
@ToString(of = ["id"])
class User(

) {

    @Id
    val id: UUID = UUID.randomUUID()

}
