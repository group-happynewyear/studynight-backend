package kr.happynewyear.library.messaging.consumer

import kr.happynewyear.library.exception.RunnerWrappers
import kr.happynewyear.notification.message.ExceptionNotifier
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class ConsumeProcessor(
    private val exceptionNotifier: ExceptionNotifier
) {

    @Async
    fun consume(consumeAction: Runnable) {
        RunnerWrappers.run(consumeAction) {
            exceptionNotifier.send(it)
        }
    }

}
