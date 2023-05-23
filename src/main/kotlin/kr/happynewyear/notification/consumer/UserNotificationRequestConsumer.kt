package kr.happynewyear.notification.consumer

import com.github.josh910830.portablemq.core.consumer.Consume
import com.github.josh910830.portablemq.core.consumer.Consumer
import com.github.josh910830.portablemq.spring.consumer.SpringListener
import kr.happynewyear.library.constant.Topics.Notification.Companion.USER_NOTIFICATION_REQUEST
import kr.happynewyear.notification.application.dto.Notification
import kr.happynewyear.notification.application.service.ChannelService
import kr.happynewyear.notification.application.service.NotificationService
import kr.happynewyear.notification.message.UserNotificationRequest

@Consumer
class UserNotificationRequestConsumer(
    private val channelService: ChannelService,
    private val notificationService: NotificationService
) {

    @Consume
    @SpringListener(USER_NOTIFICATION_REQUEST)
    fun consume(message: UserNotificationRequest) = with(message) {
        channelService.list(userId)
            .map { Notification.of(it, title, content) }
            .forEach { notificationService.notify(it) }
    }

}
