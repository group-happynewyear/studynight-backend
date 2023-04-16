package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.constant.ChannelType.MAIL
import kr.happynewyear.notification.constant.ChannelType.SLACK
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors.joining

@Service
@Transactional(readOnly = true)
class AlertService(
    private val channelService: ChannelService,
    private val notificationService: NotificationService
) {

    fun send(applicationName: String, exceptionSummary: String, stacktrace: List<String>) {
        val head = "[$applicationName] $exceptionSummary"
        val fullStacktrace = stacktrace.stream().collect(joining("\n"))
        val coreStacktrace = stacktrace.stream()
            .filter { it.startsWith("kr.happynewyear") }.findFirst()
            .orElseGet { stacktrace[0] }

        val alertChannels = channelService.alertChannels(applicationName)
        alertChannels.forEach {
            when (it.type) {
                MAIL -> notificationService.mail(it.address, head, "${head}\n${fullStacktrace}")
                SLACK -> notificationService.slack(it.address, "${head}\n${coreStacktrace}")
            }
        }
    }

}
