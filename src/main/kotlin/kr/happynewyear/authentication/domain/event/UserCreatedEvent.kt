package kr.happynewyear.authentication.domain.event

import kr.happynewyear.authentication.domain.model.User
import kr.happynewyear.library.event.DomainEvent

data class UserCreatedEvent(
    val user: User
) : DomainEvent
