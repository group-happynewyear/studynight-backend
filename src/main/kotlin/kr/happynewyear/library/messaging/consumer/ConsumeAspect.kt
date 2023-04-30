package kr.happynewyear.library.messaging.consumer

import kr.happynewyear.library.exception.ExceptionNotifier
import kr.happynewyear.library.messaging.BrokerType
import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.consumer.deadletter.DeadletterHandler
import kr.happynewyear.library.messaging.consumer.log.ConsumptionLogHandler
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.task.TaskExecutor
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Aspect
@Component
class ConsumeAspect(
    private val taskExecutor: TaskExecutor,
    private val consumptionLogHandler: ConsumptionLogHandler,
    private val exceptionNotifier: ExceptionNotifier,
    private val deadletterHandler: DeadletterHandler
) {

    @Around("@annotation(consume) && args(message)")
    fun consume(joinPoint: ProceedingJoinPoint, consume: Consume, message: Message) {
        taskExecutor.execute {
            consume(
                consume.consumerGroup, consume.brokerType,
                message, { joinPoint.proceed() },
                consume.alert, consume.deadletter, consume.consumptionLog
            )
        }
    }

    private fun <T : Message> consume(
        consumerGroup: String, brokerType: BrokerType,
        message: T, consumeAction: Consumer<T>,
        alert: Boolean, deadletter: Boolean, consumptionLog: Boolean
    ) {
        try {
            if (consumptionLog) consumptionLogHandler.handle(consumerGroup, message, consumeAction)
            else consumeAction.accept(message)
        } catch (e: Exception) {
            if (alert) exceptionNotifier.send(e)
            if (deadletter) deadletterHandler.create(message, brokerType)
        }
    }

}
