package kr.happynewyear.notification.consumer

import com.github.josh910830.portablemq.core.consumer.Consume
import com.github.josh910830.portablemq.core.consumer.Consumer
import com.github.josh910830.portablemq.spring.consumer.SpringListener
import kr.happynewyear.library.constant.Topics.Companion.APPLICATION_DEADLETTER_SEND_REQUEST
import kr.happynewyear.notification.application.service.ApplicationService
import kr.happynewyear.notification.message.ApplicationDeadletterSendRequest

@Consumer
class ApplicationDeadletterSendRequestConsumer(
    private val applicationService: ApplicationService
) {

    @Consume(useDeadletter = false)
    @SpringListener(APPLICATION_DEADLETTER_SEND_REQUEST)
    fun consume(message: ApplicationDeadletterSendRequest) {
        applicationService.deadletter(
            message.applicationName, message.topic,
            message.messageContent, message.redriveLink
        )
    }

}
