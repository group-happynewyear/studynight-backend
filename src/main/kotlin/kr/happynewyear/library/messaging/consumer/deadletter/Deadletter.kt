package kr.happynewyear.library.messaging.consumer.deadletter

import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.Message
import java.time.LocalDateTime

data class Deadletter(
    val messageContent: String, val exceptionSummary: String,
    val messageClass: String, val brokerType: BrokerType,
    val timestamp: LocalDateTime
) {
    companion object {
        fun from(message: Message, exception: Exception, brokerType: BrokerType): Deadletter {
            return Deadletter(
                JsonMarshallers.write(message),
                "${exception.javaClass.simpleName}: ${exception.message}",
                message.javaClass.name, brokerType,
                LocalDateTime.now()
            )

        }
    }
}
