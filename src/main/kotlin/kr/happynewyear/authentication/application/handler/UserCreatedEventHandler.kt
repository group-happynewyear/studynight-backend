package kr.happynewyear.authentication.application.handler

import kr.happynewyear.authentication.domain.event.UserCreatedEvent
import kr.happynewyear.library.notification.NotificationRequestFacade
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserCreatedEventHandler(
    private val notificationRequestFacade: NotificationRequestFacade
) {

    @EventListener
    fun on(e: UserCreatedEvent) {
        notificationRequestFacade.channel(e.user.id.toString(), e.user.email)
    }

}