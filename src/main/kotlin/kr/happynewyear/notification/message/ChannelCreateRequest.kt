package kr.happynewyear.notification.message

import com.github.josh910830.portablemq.core.message.IdentifiableMessage
import kr.happynewyear.notification.constant.AddressType
import kr.happynewyear.notification.constant.OwnerType
import java.util.*

data class ChannelCreateRequest(
    val ownerType: String,
    val ownerId: String,
    val addressType: String,
    val addressValue: String,
) : IdentifiableMessage() {
    companion object {

        fun ofUserMail(userId: UUID, email: String): ChannelCreateRequest {
            return ChannelCreateRequest(
                OwnerType.USER.name, userId.toString(),
                AddressType.MAIL.name, email
            )
        }

    }
}
