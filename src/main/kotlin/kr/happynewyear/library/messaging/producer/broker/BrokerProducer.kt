package kr.happynewyear.library.messaging.producer.broker

import kr.happynewyear.library.messaging.Message

interface BrokerProducer {

    fun produce(message: Message)

}
