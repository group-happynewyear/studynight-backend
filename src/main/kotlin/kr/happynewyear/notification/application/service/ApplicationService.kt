package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.constant.ChannelType
import kr.happynewyear.notification.constant.ChannelType.MAIL
import kr.happynewyear.notification.constant.ChannelType.SLACK
import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
@Transactional(readOnly = true)
class ApplicationService(
    private val channelRepository: ChannelRepository,
    private val notificationService: NotificationService,
    @Value("\${notification.alert.default-channel.type}") private val defaultAlertChannelType: ChannelType,
    @Value("\${notification.alert.default-channel.address}") private val defaultAlertChannelAddress: String
) {

    fun alert(
        applicationName: String,
        exceptionType: String, exceptionMessage: String, stacktrace: List<String>
    ) {
        val head = "[$applicationName] $exceptionType: $exceptionMessage"
        val fullStacktrace = stacktrace.stream().collect(Collectors.joining("\n"))
        val coreStacktrace = stacktrace.stream()
            .filter { it.startsWith("kr.happynewyear") }.findFirst()
            .orElseGet { stacktrace[0] }

        val channels = channelRepository.findByApplication(applicationName)
            .ifEmpty { Channel.ofDefaultAlert(defaultAlertChannelType, defaultAlertChannelAddress) }
        channels.forEach {
            when (it.type) {
                MAIL -> notificationService.mail(it.address, head, "${head}\n${fullStacktrace}") {}
                SLACK -> notificationService.slack(it.address, "${head}\n${coreStacktrace}") {}
            }
        }
    }

}
