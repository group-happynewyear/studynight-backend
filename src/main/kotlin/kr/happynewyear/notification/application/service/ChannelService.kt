package kr.happynewyear.notification.application.service

import kr.happynewyear.notification.application.dto.ChannelResult
import kr.happynewyear.notification.constant.ChannelType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ChannelService(
    @Value("\${notification.alert.default-channel.type}") private val defaultChannelType: ChannelType,
    @Value("\${notification.alert.default-channel.address}") private val defaultChannelAddress: String
) {

    fun alertChannels(applicationName: String): List<ChannelResult> {
        // TODO("impl")
        return listOf(ChannelResult(defaultChannelType, defaultChannelAddress))
    }

}
