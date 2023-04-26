package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.exception.RunnerWrappers
import kr.happynewyear.notification.message.ExceptionNotifier
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class ProduceProcessor(
    private val exceptionNotifier: ExceptionNotifier
) {

    @Async
    fun produce(produceAction: Runnable) {
        RunnerWrappers.run(produceAction) {
            exceptionNotifier.send(it)
        }
    }

}
