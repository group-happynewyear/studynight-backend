package kr.happynewyear.notification.consumer

import com.github.josh910830.portablemq.core.consumer.Consume
import com.github.josh910830.portablemq.core.consumer.Consumer
import com.github.josh910830.portablemq.spring.consumer.SpringListener
import kr.happynewyear.library.constant.Topics.Notification.Companion.APPLICATION_NOTIFICATION_REQUEST
import kr.happynewyear.notification.application.dto.Notification
import kr.happynewyear.notification.application.service.ChannelService
import kr.happynewyear.notification.application.service.NotificationService
import kr.happynewyear.notification.message.ApplicationNotificationRequest

@Consumer
class ApplicationNotificationRequestConsumer(
    private val channelService: ChannelService,
    private val notificationService: NotificationService
) {

    @Consume(useConsumptionLog = false, useDeadletter = false, useBadletter = false)
    @SpringListener(APPLICATION_NOTIFICATION_REQUEST)
    fun consume(message: ApplicationNotificationRequest) = with(message) {
        channelService.list(applicationName)
            .map { Notification.of(it, title, content) }
            .forEach { notificationService.notify(it) }
    }

}
