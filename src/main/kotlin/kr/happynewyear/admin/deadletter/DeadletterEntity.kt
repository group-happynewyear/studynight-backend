package kr.happynewyear.admin.deadletter

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.consumer.deadletter.Deadletter
import kr.happynewyear.library.utility.Randoms
import java.time.LocalDateTime

@Entity
@Table(name = "deadletters")
class DeadletterEntity(
    messageContent: String, exceptionSummary: String,
    messageClass: String, brokerType: String,
    timestamp: LocalDateTime
) {

    companion object {
        fun from(deadletter: Deadletter): DeadletterEntity {
            return DeadletterEntity(
                deadletter.messageContent, deadletter.exceptionSummary,
                deadletter.messageClass, deadletter.brokerType.name,
                deadletter.timestamp
            )
        }
    }

    fun toDeadletter(): Deadletter {
        return Deadletter(messageContent, exceptionSummary, messageClass, BrokerType.valueOf(brokerType), timestamp)
    }

    @Id val id: String = Randoms.uuidString()
    @Column(columnDefinition = "TEXT") val messageContent: String = messageContent
    val exceptionSummary: String = exceptionSummary
    val messageClass: String = messageClass
    val brokerType: String = brokerType
    val timestamp: LocalDateTime = timestamp

}
