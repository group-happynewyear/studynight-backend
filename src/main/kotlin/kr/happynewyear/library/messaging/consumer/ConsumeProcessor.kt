package kr.happynewyear.library.messaging.consumer

import kr.happynewyear.library.exception.ExceptionNotifier
import kr.happynewyear.library.exception.RunnerWrappers
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.consumer.deadletter.DeadletterHandler
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class ConsumeProcessor(
    private val exceptionNotifier: ExceptionNotifier,
    private val deadletterHandler: DeadletterHandler
) {

    @Async
    fun <T : Message> consume(
        brokerType: BrokerType,
        message: T, consumeAction: Consumer<T>,
        alert: Boolean = true, deadletter: Boolean = true, consumptionLog: Boolean = true
    ) {
        RunnerWrappers.run(
            { consumeAction.accept(message) },
            {
                if (alert) exceptionNotifier.send(it)
                if (deadletter) deadletterHandler.create(message, it, brokerType)
            }
        )
    }

}
