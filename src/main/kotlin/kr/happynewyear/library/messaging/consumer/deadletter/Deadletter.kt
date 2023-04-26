package kr.happynewyear.library.messaging.consumer.deadletter

import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.library.messaging.Message
import java.time.LocalDateTime

data class Deadletter(
    val message: String,
    val exception: String,
    val timestamp: LocalDateTime
) {
    companion object {
        fun from(message: Message, exception: Exception): Deadletter {
            return Deadletter(
                JsonMarshallers.write(message),
                "${exception.javaClass.simpleName}: ${exception.message}",
                LocalDateTime.now()
            )

        }
    }
}
