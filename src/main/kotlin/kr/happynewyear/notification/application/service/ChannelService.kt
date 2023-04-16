package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.application.dto.ChannelResult
import kr.happynewyear.notification.constant.ChannelType
import kr.happynewyear.notification.constant.OwnerType
import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ChannelService(
    private val channelRepository: ChannelRepository,
    @Value("\${notification.alert.default-channel.type}") private val defaultChannelType: ChannelType,
    @Value("\${notification.alert.default-channel.address}") private val defaultChannelAddress: String
) {

    fun alertChannels(applicationName: String): List<ChannelResult> {
        // TODO("impl")
        return listOf(ChannelResult(defaultChannelType, defaultChannelAddress))
    }


    @Transactional
    fun create(
        ownerType: OwnerType, ownerId: String,
        type: ChannelType, address: String
    ) {
        val channel = Channel.create(ownerType, ownerId, type, address)
        channelRepository.save(channel)
    }

}
