package kr.happynewyear.configuration.message

import kr.happynewyear.library.message.BrokerProducer
import kr.happynewyear.library.message.Message
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class SpringProducer(
    private val applicationEventPublisher: ApplicationEventPublisher
) : BrokerProducer {

    @Async
    override fun produce(message: Message) {
        applicationEventPublisher.publishEvent(message)
    }

}
