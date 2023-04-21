package kr.happynewyear.library.messaging.producer.broker

import kr.happynewyear.library.exception.RunnerWrappers
import kr.happynewyear.library.messaging.Message
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async

open class SpringProducer(
    private val applicationEventPublisher: ApplicationEventPublisher
) : BrokerProducer {

    @Async
    override fun produce(message: Message) {
        RunnerWrappers.run({ applicationEventPublisher.publishEvent(message) }, {})
    }

}
