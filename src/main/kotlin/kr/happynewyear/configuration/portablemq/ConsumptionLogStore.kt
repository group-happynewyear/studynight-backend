package kr.happynewyear.configuration.portablemq

import com.github.josh910830.portablemq.core.consumer.log.ConsumptionLog
import com.github.josh910830.portablemq.core.consumer.log.interfaces.ConsumptionLogStore
import org.springframework.stereotype.Component

@Component
class ConsumptionLogStore : ConsumptionLogStore {

    private val set = mutableSetOf<String>()


    override fun exists(groupId: String, messageId: String): Boolean {
        return set.contains(key(groupId, messageId))
    }

    override fun save(consumptionLog: ConsumptionLog) {
        set.add(key(consumptionLog.groupId, consumptionLog.message.id))
    }

    private fun key(groupId: String, messageId: String): String {
        return "$groupId::$messageId"
    }

}
