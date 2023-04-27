package kr.happynewyear.library.messaging.consumer.deadletter

import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.producer.BrokerProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DeadletterService(
    private val deadletterStore: DeadletterStore,
    private val brokerProducers: List<BrokerProducer>
) {

    @Transactional
    fun create(message: Message, exception: Exception, brokerType: BrokerType) {
        val deadletter = Deadletter.from(message, exception, brokerType)
        val deadletterId = deadletterStore.save(deadletter)
        // TODO notify
    }

    @Transactional
    fun requeue(deadletterId: String) {
        val deadletter = deadletterStore.findById(deadletterId)!!
        val message = JsonMarshallers.read(deadletter.messageContent, Class.forName(deadletter.messageClass)) as Message
        val brokerProducer = brokerProducers.first { it.brokerType == deadletter.brokerType }
        brokerProducer.produce(message)
        // TODO status
    }

}
