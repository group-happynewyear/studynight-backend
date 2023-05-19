package kr.happynewyear.notification.message

import com.github.josh910830.portablemq.core.message.IdentifiableMessage
import java.util.*

data class UserMailSendRequest(
    val userId: UUID,
    val title: String,
    val content: String
) : IdentifiableMessage()
