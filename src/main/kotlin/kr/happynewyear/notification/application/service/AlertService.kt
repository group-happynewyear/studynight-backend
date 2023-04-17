package kr.happynewyear.notification.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AlertService(
    private val channelService: ChannelService,
    private val notificationService: NotificationService
) {

    fun send(applicationName: String, exceptionSummary: String, stacktrace: List<String>) {
        // TODO remove
    }

}
