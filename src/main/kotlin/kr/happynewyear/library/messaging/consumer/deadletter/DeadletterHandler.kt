package kr.happynewyear.library.messaging.consumer.deadletter

import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.producer.BrokerProducer
import kr.happynewyear.library.messaging.producer.SpringProducer
import org.springframework.stereotype.Component

@Component
class DeadletterHandler(
    private val deadletterStore: DeadletterStore,
    private val deadletterNotifier: DeadletterNotifier,
    private val springProducer: SpringProducer
) {

    fun create(message: Message, brokerType: BrokerType) {
        val deadletter = Deadletter.from(message, brokerType)
        val deadletterId = deadletterStore.put(deadletter)
        deadletterNotifier.send(deadletterId, message)
    }


    fun requeue(deadletterId: String) {
        val deadletter = deadletterStore.pop(deadletterId)!!
        val producer = selectProducer(deadletter)
        val message = extractMessage(deadletter)
        producer.produce(message)
    }

    private fun selectProducer(deadletter: Deadletter): BrokerProducer {
        when (deadletter.brokerType) {
            SPRING -> return springProducer
            // TODO kafka
        }
    }

    private fun extractMessage(deadletter: Deadletter): Message {
        val json = deadletter.messageContent
        val clazz = Class.forName(deadletter.messageClass)
        return JsonMarshallers.read(json, clazz) as Message
    }

}
