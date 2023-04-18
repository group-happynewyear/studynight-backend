package kr.happynewyear.library.message

interface Producer<T : Message> {

    fun produce(message: T) {
        BrokerProducerHolder.brokerProducer.produce(message)
    }

}
