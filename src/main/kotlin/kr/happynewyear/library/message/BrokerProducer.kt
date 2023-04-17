package kr.happynewyear.library.message

interface BrokerProducer {

    fun produce(message: Message)

}
