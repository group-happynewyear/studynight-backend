package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.BrokerType.SPRING
import kr.happynewyear.library.messaging.consumer.Consume
import kr.happynewyear.notification.application.service.ApplicationService
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationAlertSendRequestConsumer(
    private val applicationService: ApplicationService
) {

    @EventListener
    @Consume("alert", SPRING)
    fun consume(message: ApplicationAlertSendRequest) {
        applicationService.alert(
            message.applicationName,
            message.exceptionType, message.exceptionMessage, message.stacktrace
        )
    }

}
