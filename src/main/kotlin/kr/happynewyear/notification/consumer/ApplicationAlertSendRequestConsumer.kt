package kr.happynewyear.notification.consumer

import com.github.josh910830.portablemq.core.consumer.Consume
import com.github.josh910830.portablemq.core.consumer.Consumer
import com.github.josh910830.portablemq.spring.consumer.SpringListener
import kr.happynewyear.library.constant.Topics.Companion.APPLICATION_ALERT_SEND_REQUEST
import kr.happynewyear.notification.application.service.ApplicationService
import kr.happynewyear.notification.message.ApplicationAlertSendRequest

@Consumer
class ApplicationAlertSendRequestConsumer(
    private val applicationService: ApplicationService
) {

    @Consume
    @SpringListener(APPLICATION_ALERT_SEND_REQUEST)
    fun consume(message: ApplicationAlertSendRequest) {
        applicationService.alert(
            message.applicationName,
            message.exceptionType, message.exceptionMessage, message.stacktrace
        )
    }

}
