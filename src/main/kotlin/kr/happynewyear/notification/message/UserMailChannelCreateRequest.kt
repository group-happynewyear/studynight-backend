package kr.happynewyear.notification.message

import com.github.josh910830.portablemq.core.message.IdentifiableMessage
import java.util.*

data class UserMailChannelCreateRequest(
    val userId: UUID,
    val email: String
) : IdentifiableMessage()
