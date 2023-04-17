package kr.happynewyear.notification.consumer

import kr.happynewyear.notification.application.service.ApplicationService
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationAlertSendRequestConsumer(
    private val applicationService: ApplicationService
) {

    @EventListener
    fun on(m: ApplicationAlertSendRequest) {
        applicationService.alert(m.applicationName, m.exceptionType, m.exceptionMessage, m.stacktrace)
    }

}
