package kr.happynewyear.notification.infrastructure.database

import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.model.ChannelOwner
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ChannelRepositoryAdapter(
    private val channelJpaRepository: ChannelJpaRepository
) : ChannelRepository {

    override fun save(channel: Channel) {
        channelJpaRepository.save(channel)
    }


    override fun findByApplication(applicationName: String): List<Channel> {
        val owner = ChannelOwner.ofApplication(applicationName)
        return channelJpaRepository.findByOwner(owner)
    }

    override fun findByUser(userId: UUID): List<Channel> {
        val owner = ChannelOwner.ofUser(userId)
        return channelJpaRepository.findByOwner(owner)
    }

}
