package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class UserService(
    private val channelRepository: ChannelRepository
) {

    @Transactional
    fun createMailChannel(userId: UUID, email: String) {
        val channel = Channel.ofUserMail(userId, email)
        channelRepository.save(channel)
    }

}
