package kr.happynewyear.configuration.messaging.log

import kr.happynewyear.library.messaging.consumer.log.ConsumptionLog
import kr.happynewyear.library.messaging.consumer.log.ConsumptionLogStore
import org.springframework.stereotype.Repository

@Repository
class ConsumptionLogStoreAdapter(
    private val consumptionLogJpaRepository: ConsumptionLogJpaRepository
) : ConsumptionLogStore {

    override fun put(consumptionLog: ConsumptionLog) {
        val consumptionLogEntity = ConsumptionLogEntity.from(consumptionLog)
        consumptionLogJpaRepository.save(consumptionLogEntity)
    }

    override fun contains(consumerGroup: String, messageId: String): Boolean {
        return consumptionLogJpaRepository.existsByConsumerGroupAndMessageId(consumerGroup, messageId)
    }

}
