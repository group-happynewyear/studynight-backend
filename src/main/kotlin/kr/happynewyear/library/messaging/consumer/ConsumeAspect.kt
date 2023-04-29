package kr.happynewyear.library.messaging.consumer

import kr.happynewyear.library.messaging.Message
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class ConsumeAspect(
    private val consumeProcessor: ConsumeProcessor
) {

    @Around("@annotation(consume) && args(message)")
    fun consume(joinPoint: ProceedingJoinPoint, consume: Consume, message: Message) {
        consumeProcessor.consume(
            consume.consumerGroup, consume.brokerType,
            message, { joinPoint.proceed() },
            consume.alert, consume.deadletter, consume.consumptionLog
        )
    }

}
