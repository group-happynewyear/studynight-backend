package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.application.exception.ChannelNotFoundException
import kr.happynewyear.notification.constant.AddressType
import kr.happynewyear.notification.constant.OwnerType
import kr.happynewyear.notification.constant.OwnerType.APPLICATION
import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.model.ChannelAddress
import kr.happynewyear.notification.domain.model.ChannelOwner
import kr.happynewyear.notification.domain.repository.ChannelRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class ChannelService(
    private val channelRepository: ChannelRepository,
    @Value("\${notification.default-application-address.type}") private val defaultAppAddrType: AddressType,
    @Value("\${notification.default-application-address.value}") private val defaultAppAddrVal: String
) {
    private val defaultApplicationChannel = listOf(
        Channel(ChannelOwner(APPLICATION, "default"), ChannelAddress(defaultAppAddrType, defaultAppAddrVal))
    )

    @Transactional
    fun create(
        ownerType: OwnerType, ownerId: String,
        addressType: AddressType, addressValue: String
    ) {
        val owner = ChannelOwner(ownerType, ownerId)
        val address = ChannelAddress(addressType, addressValue)
        val channel = Channel(owner, address)
        channelRepository.save(channel)
    }


    fun list(applicationName: String): List<Channel> {
        return channelRepository.findByApplication(applicationName).ifEmpty { defaultApplicationChannel }
    }

    fun list(userId: UUID): List<Channel> {
        val channels = channelRepository.findByUser(userId)
        if (channels.isEmpty()) throw ChannelNotFoundException()
        return channels
    }

}
