package kr.happynewyear.library.messaging.consumer

import kr.happynewyear.library.exception.RunnerWrappers
import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.consumer.deadletter.Deadletter
import kr.happynewyear.library.messaging.consumer.deadletter.DeadletterStore
import kr.happynewyear.notification.message.ExceptionNotifier
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class ConsumeProcessor(
    private val exceptionNotifier: ExceptionNotifier,
    private val deadletterStore: DeadletterStore
) {

    @Async
    fun <T : Message> consume(message: T, consumeAction: Consumer<T>) {
        RunnerWrappers.run(
            { consumeAction.accept(message) },
            {
                exceptionNotifier.send(it)

                val deadletter = Deadletter.from(message, it)
                deadletterStore.save(deadletter)
            }
        )
    }

}
