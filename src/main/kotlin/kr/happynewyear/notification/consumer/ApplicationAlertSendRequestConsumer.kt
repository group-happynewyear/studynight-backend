package kr.happynewyear.notification.consumer

import kr.happynewyear.library.messaging.consumer.ConsumeProcessor
import kr.happynewyear.notification.application.service.ApplicationService
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationAlertSendRequestConsumer(
    private val consumeProcessor: ConsumeProcessor,
    private val applicationService: ApplicationService
) {

    @EventListener
    fun consume(message: ApplicationAlertSendRequest) {
        consumeProcessor.consume(message) {
            applicationService.alert(
                it.applicationName,
                it.exceptionType, it.exceptionMessage, it.stacktrace
            )
        }
    }

}
