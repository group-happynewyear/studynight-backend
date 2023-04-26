package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.exception.RunnerWrappers
import kr.happynewyear.library.messaging.Message
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class SpringProducer(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @Async
    fun produce(message: Message) {
        RunnerWrappers.run({ applicationEventPublisher.publishEvent(message) }, {})
    }

}
