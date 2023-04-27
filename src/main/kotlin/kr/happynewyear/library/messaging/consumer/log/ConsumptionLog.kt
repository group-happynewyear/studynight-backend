package kr.happynewyear.library.messaging.consumer.log

import kr.happynewyear.library.messaging.Message
import java.time.LocalDateTime

data class ConsumptionLog(
    val consumerGroup: String,
    val messageId: String,
    val timestamp: LocalDateTime
) {
    companion object {
        fun from(consumerGroup: String, message: Message): ConsumptionLog {
            return ConsumptionLog(consumerGroup, message.id, LocalDateTime.now())
        }
    }
}
