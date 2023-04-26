package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.messaging.Message
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringProducer(
    private val produceProcessor: ProduceProcessor,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun produce(message: Message) {
        produceProcessor.produce { applicationEventPublisher.publishEvent(message) }
    }

}
