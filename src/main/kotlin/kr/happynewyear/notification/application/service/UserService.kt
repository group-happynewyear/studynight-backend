package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.application.exception.UserChannelNotFoundException
import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class UserService(
    private val channelRepository: ChannelRepository,
    private val notificationService: NotificationService
) {

    @Transactional
    fun createMailChannel(userId: UUID, email: String) {
        val channel = Channel.ofUserMail(userId, email)
        channelRepository.save(channel)
    }

    fun sendMail(userId: UUID, title: String, content: String) {
        val channels = channelRepository.findMailByUser(userId.toString())
        if (channels.isEmpty()) throw UserChannelNotFoundException()
        channels.forEach { notificationService.mail(it.address, title, content) }
    }

}
