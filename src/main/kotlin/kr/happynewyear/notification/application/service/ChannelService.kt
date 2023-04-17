package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.constant.ChannelType
import kr.happynewyear.notification.constant.OwnerType
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ChannelService(
    private val channelRepository: ChannelRepository,
) {

    @Transactional
    fun create(
        ownerType: OwnerType, ownerId: String,
        type: ChannelType, address: String
    ) {
        // TODO remove
    }

}
