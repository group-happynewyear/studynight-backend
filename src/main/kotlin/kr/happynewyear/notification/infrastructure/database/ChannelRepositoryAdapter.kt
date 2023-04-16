package kr.happynewyear.notification.infrastructure.database

import kr.happynewyear.notification.constant.ChannelType.MAIL
import kr.happynewyear.notification.constant.OwnerType.USER
import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.stereotype.Repository

@Repository
class ChannelRepositoryAdapter(
    private val channelJpaRepository: ChannelJpaRepository
) : ChannelRepository {

    override fun save(channel: Channel) {
        channelJpaRepository.save(channel)
    }

    override fun findMailByUser(userId: String): List<Channel> {
        return channelJpaRepository.findByOwnerTypeAndOwnerIdAndType(USER, userId, MAIL)
    }

}
