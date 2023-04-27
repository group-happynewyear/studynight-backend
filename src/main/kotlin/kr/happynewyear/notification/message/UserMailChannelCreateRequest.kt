package kr.happynewyear.notification.message

import kr.happynewyear.library.messaging.Message
import java.util.*

data class UserMailChannelCreateRequest(
    val userId: UUID,
    val email: String
) : Message()
