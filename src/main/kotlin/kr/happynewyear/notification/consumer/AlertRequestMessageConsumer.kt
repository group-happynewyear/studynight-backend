package kr.happynewyear.notification.consumer

import kr.happynewyear.library.notification.message.AlertRequestMessage
import kr.happynewyear.notification.application.service.AlertService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AlertRequestMessageConsumer(
    private val alertService: AlertService
) {

    @EventListener
    fun consume(message: AlertRequestMessage) {
        alertService.send(message.applicationName, message.exceptionSummary, message.stacktrace)
    }

}
