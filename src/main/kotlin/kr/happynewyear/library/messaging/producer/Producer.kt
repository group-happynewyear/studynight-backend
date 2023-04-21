package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.producer.broker.BrokerProducerHolder

interface Producer<T : Message> {

    fun produce(message: T) {
        BrokerProducerHolder.brokerProducer.produce(message)
    }

}
