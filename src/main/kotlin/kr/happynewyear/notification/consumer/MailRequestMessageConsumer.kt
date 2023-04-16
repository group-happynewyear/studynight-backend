package kr.happynewyear.notification.consumer

import kr.happynewyear.library.notification.message.MailRequestMessage
import kr.happynewyear.notification.application.service.NotificationService
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class MailRequestMessageConsumer(
    private val channelRepository: ChannelRepository,
    private val notificationService: NotificationService
) {

    @EventListener
    fun consume(m: MailRequestMessage) {
        val channels = channelRepository.findMailByUser(m.userId)
        channels.forEach { notificationService.mail(it.address, m.title, m.content) }
    }

}
