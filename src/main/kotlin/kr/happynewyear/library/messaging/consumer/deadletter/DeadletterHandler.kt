package kr.happynewyear.library.messaging.consumer.deadletter

import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.producer.Producer
import kr.happynewyear.library.messaging.producer.SpringRequeueProducer
import org.springframework.stereotype.Component

@Component
class DeadletterHandler(
    private val deadletterStore: DeadletterStore,
    private val deadletterNotifier: DeadletterNotifier,
    private val springRequeueProducer: SpringRequeueProducer
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

    private fun selectProducer(deadletter: Deadletter): Producer<Message> {
        when (deadletter.brokerType) {
            SPRING -> return springRequeueProducer
            // TODO kafka
        }
    }

    private fun extractMessage(deadletter: Deadletter): Message {
        val json = deadletter.messageContent
        val clazz = Class.forName(deadletter.messageClass)
        return JsonMarshallers.read(json, clazz) as Message
    }

}
