package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.Message
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringProducer(
    private val applicationEventPublisher: ApplicationEventPublisher
) : BrokerProducer {

    override val brokerType = SPRING

    override fun produce(message: Message) {
        applicationEventPublisher.publishEvent(message)
    }

}
