package kr.happynewyear.configuration.messaging

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.library.messaging.consumer.deadletter.Deadletter
import java.time.LocalDateTime

@Entity
@Table(
    name = "deadletters"
)
class DeadletterEntity(
    message: String,
    exception: String,
    timestamp: LocalDateTime
) : Identifiable() {

    companion object {
        fun from(deadletter: Deadletter): DeadletterEntity {
            return DeadletterEntity(
                deadletter.message,
                deadletter.exception,
                deadletter.timestamp
            )
        }
    }

    @Column(
        name = "message",
        nullable = false, updatable = true, unique = false
    )
    val message: String = message

    @Column(
        name = "exception",
        nullable = false, updatable = false, unique = false
    )
    val exception: String = exception

    @Column(
        name = "timestamp",
        nullable = false, updatable = false, unique = false
    )
    val timestamp: LocalDateTime = timestamp

}
