package kr.happynewyear.configuration.messaging.log

import org.springframework.data.jpa.repository.JpaRepository

interface ConsumptionLogJpaRepository : JpaRepository<ConsumptionLogEntity, String> {

    fun existsByConsumerGroupAndMessageId(consumerGroup: String, messageId: String): Boolean

}
