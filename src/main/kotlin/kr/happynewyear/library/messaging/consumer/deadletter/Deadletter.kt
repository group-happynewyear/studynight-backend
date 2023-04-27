package kr.happynewyear.library.messaging.consumer.deadletter

import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.Message

data class Deadletter(
    val messageContent: String,
    val messageClass: String, val brokerType: BrokerType,
) {
    companion object {
        fun from(message: Message, brokerType: BrokerType): Deadletter {
            return Deadletter(
                JsonMarshallers.write(message),
                message.javaClass.name, brokerType,
            )
        }
    }
}
