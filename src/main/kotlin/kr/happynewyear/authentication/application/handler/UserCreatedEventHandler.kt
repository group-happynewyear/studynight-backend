package kr.happynewyear.authentication.application.handler

import kr.happynewyear.authentication.domain.event.UserCreatedEvent
import kr.happynewyear.notification.message.UserMailChannelCreateRequest
import kr.happynewyear.notification.message.UserMailChannelCreateRequestProducer
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserCreatedEventHandler(
    private val userMailChannelCreateRequestProducer: UserMailChannelCreateRequestProducer
) {

    @EventListener
    fun on(e: UserCreatedEvent) {
        val message = UserMailChannelCreateRequest(e.user.id, e.user.email)
        userMailChannelCreateRequestProducer.produce(message)
    }

}
