package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.Message

interface BrokerProducer {

    val brokerType: BrokerType

    fun produce(message: Message)

}
