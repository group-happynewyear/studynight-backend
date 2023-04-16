package kr.happynewyear.notification.consumer

import kr.happynewyear.library.notification.message.ChannelRequestMessage
import kr.happynewyear.notification.application.service.ChannelService
import kr.happynewyear.notification.constant.ChannelType.MAIL
import kr.happynewyear.notification.constant.OwnerType.USER
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ChannelRequestMessageConsumer(
    private val channelService: ChannelService
) {

    @EventListener
    fun consume(m: ChannelRequestMessage) {
        channelService.create(
            USER, m.userId,
            MAIL, m.email
        )
    }

}
