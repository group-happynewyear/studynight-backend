package kr.happynewyear.notification.message

import com.github.josh910830.portablemq.core.message.IdentifiableMessage
import java.util.*

data class UserNotificationRequest(
    val userId: UUID,
    val title: String,
    val content: String
) : IdentifiableMessage() {
    companion object {

        fun of(userId: UUID, title: String, content: String): UserNotificationRequest {
            return UserNotificationRequest(userId, title, content)
        }

    }
}
