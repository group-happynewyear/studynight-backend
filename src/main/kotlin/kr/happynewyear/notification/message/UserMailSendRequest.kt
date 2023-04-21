package kr.happynewyear.notification.message

import kr.happynewyear.library.messaging.Message
import java.util.*

data class UserMailSendRequest(
    val userId: UUID,
    val title: String,
    val content: String
) : Message
