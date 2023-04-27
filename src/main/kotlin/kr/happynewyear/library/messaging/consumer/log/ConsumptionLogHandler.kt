package kr.happynewyear.library.messaging.consumer.log

import kr.happynewyear.library.messaging.Message
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class ConsumptionLogHandler(
    private val consumptionLogStore: ConsumptionLogStore
) {

    fun <T : Message> handle(consumerGroup: String, message: T, consumeAction: Consumer<T>) {
        if (consumptionLogStore.contains(consumerGroup, message.id)) return

        consumeAction.accept(message)

        val consumptionLog = ConsumptionLog.from(consumerGroup, message)
        consumptionLogStore.put(consumptionLog)
    }

}
