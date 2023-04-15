package kr.happynewyear.configuration.message

import kr.happynewyear.library.message.Message
import kr.happynewyear.library.message.Producer
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class ApplicationMessageProducer(
    private val applicationEventPublisher: ApplicationEventPublisher
) : Producer {

    @Async
    override fun produce(message: Message) {
        applicationEventPublisher.publishEvent(message)
    }

}
