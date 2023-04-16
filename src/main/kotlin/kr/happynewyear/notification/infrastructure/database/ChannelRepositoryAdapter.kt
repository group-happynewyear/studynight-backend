package kr.happynewyear.notification.infrastructure.database

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

}
