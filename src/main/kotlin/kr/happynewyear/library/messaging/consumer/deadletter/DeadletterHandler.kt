package kr.happynewyear.library.messaging.consumer.deadletter

import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.producer.BrokerProducer
import org.springframework.stereotype.Component

@Component
class DeadletterHandler(
    private val deadletterStore: DeadletterStore,
    private val brokerProducers: List<BrokerProducer>,
    private val deadletterNotifier: DeadletterNotifier
) {

    fun create(message: Message, exception: Exception, brokerType: BrokerType) {
        val deadletter = Deadletter.from(message, exception, brokerType)
        val deadletterId = deadletterStore.add(deadletter)
        deadletterNotifier.send(deadletterId, message)
    }

    fun requeue(deadletterId: String) {
        val deadletter = deadletterStore.pop(deadletterId)!!
        val message = JsonMarshallers.read(deadletter.messageContent, Class.forName(deadletter.messageClass)) as Message
        val brokerProducer = brokerProducers.first { it.brokerType == deadletter.brokerType }
        brokerProducer.produce(message)
    }

}
