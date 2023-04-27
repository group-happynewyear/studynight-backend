package kr.happynewyear.library.messaging.consumer.log

interface ConsumptionLogStore {

    fun put(consumptionLog: ConsumptionLog)
    fun contains(consumerGroup: String, messageId: String): Boolean

}
