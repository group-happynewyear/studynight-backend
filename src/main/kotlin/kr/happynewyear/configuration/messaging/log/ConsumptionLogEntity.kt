package kr.happynewyear.configuration.messaging.log

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kr.happynewyear.library.messaging.consumer.log.ConsumptionLog
import kr.happynewyear.library.utility.Randoms
import java.time.LocalDateTime

@Entity
@Table(name = "consumption_logs")
class ConsumptionLogEntity(
    consumerGroup: String,
    messageId: String,
    timestamp: LocalDateTime
) {

    companion object {
        fun from(consumptionLog: ConsumptionLog): ConsumptionLogEntity {
            return ConsumptionLogEntity(
                consumptionLog.consumerGroup, consumptionLog.messageId,
                consumptionLog.timestamp
            )
        }
    }

    @Id val id: String = Randoms.uuidString()
    val consumerGroup: String = consumerGroup
    val messageId: String = messageId
    val timestamp: LocalDateTime = timestamp

}
