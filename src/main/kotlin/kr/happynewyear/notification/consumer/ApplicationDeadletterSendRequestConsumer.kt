package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.consumer.ConsumeProcessor
import kr.happynewyear.notification.application.service.ApplicationService
import kr.happynewyear.notification.message.ApplicationDeadletterSendRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationDeadletterSendRequestConsumer(
    private val consumeProcessor: ConsumeProcessor,
    private val applicationService: ApplicationService
) {

    @EventListener
    fun consume(message: ApplicationDeadletterSendRequest) {
        consumeProcessor.consume("deadletter", SPRING, message, {
            applicationService.deadletter(
                it.applicationName, it.messageType,
                it.messageContent, it.requeueLink
            )
        }, deadletter = false)
    }

}
