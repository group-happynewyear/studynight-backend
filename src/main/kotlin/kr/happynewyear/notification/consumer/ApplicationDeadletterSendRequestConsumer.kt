package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.consumer.Consume
import kr.happynewyear.notification.application.service.ApplicationService
import kr.happynewyear.notification.message.ApplicationDeadletterSendRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationDeadletterSendRequestConsumer(
    private val applicationService: ApplicationService
) {

    @EventListener
    @Consume("deadletter", SPRING, deadletter = false)
    fun consume(message: ApplicationDeadletterSendRequest) {
        applicationService.deadletter(
            message.applicationName, message.messageType,
            message.messageContent, message.requeueLink
        )
    }

}
