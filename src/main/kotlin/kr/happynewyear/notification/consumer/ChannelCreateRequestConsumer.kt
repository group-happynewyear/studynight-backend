package kr.happynewyear.notification.consumer

import com.github.josh910830.portablemq.core.consumer.Consume
import com.github.josh910830.portablemq.core.consumer.Consumer
import com.github.josh910830.portablemq.spring.consumer.SpringListener
import kr.happynewyear.library.constant.Topics.Notification.Companion.CHANNEL_CREATE_REQUEST
import kr.happynewyear.notification.application.service.ChannelService
import kr.happynewyear.notification.constant.AddressType
import kr.happynewyear.notification.constant.OwnerType
import kr.happynewyear.notification.message.ChannelCreateRequest

@Consumer
class ChannelCreateRequestConsumer(
    private val channelService: ChannelService
) {

    @Consume
    @SpringListener(CHANNEL_CREATE_REQUEST)
    fun consume(message: ChannelCreateRequest) = with(message) {
        channelService.create(
            OwnerType.valueOf(ownerType), ownerId,
            AddressType.valueOf(addressType), addressValue
        )
    }

}
