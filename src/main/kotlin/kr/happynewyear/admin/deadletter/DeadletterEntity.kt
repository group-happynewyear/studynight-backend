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
    messageContent: String,
    messageClass: String, brokerType: String,
) {

    companion object {
        fun from(deadletter: Deadletter): DeadletterEntity {
            return DeadletterEntity(
                deadletter.messageContent,
                deadletter.messageClass, deadletter.brokerType.name,
            )
        }
    }

    fun toDeadletter(): Deadletter {
        return Deadletter(messageContent, messageClass, BrokerType.valueOf(brokerType))
    }

    @Id val id: String = Randoms.uuidString()
    @Column(columnDefinition = "TEXT") val messageContent: String = messageContent
    val messageClass: String = messageClass
    val brokerType: String = brokerType
    val timestamp: LocalDateTime = LocalDateTime.now()

}
