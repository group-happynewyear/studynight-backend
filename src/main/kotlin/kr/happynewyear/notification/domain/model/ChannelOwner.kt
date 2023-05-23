package kr.happynewyear.notification.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import kr.happynewyear.notification.constant.OwnerType
import kr.happynewyear.notification.constant.OwnerType.APPLICATION
import kr.happynewyear.notification.constant.OwnerType.USER
import java.util.*

@Embeddable
data class ChannelOwner(

    @Enumerated(STRING)
    @Column(
        name = "owner_type",
        nullable = false, updatable = false, unique = false
    )
    private val type: OwnerType,

    @Column(
        name = "owner_id",
        nullable = false, updatable = false, unique = false
    )
    private val id: String

) {
    companion object {

        fun ofApplication(applicationName: String): ChannelOwner {
            return ChannelOwner(APPLICATION, applicationName)
        }

        fun ofUser(userId: UUID): ChannelOwner {
            return ChannelOwner(USER, userId.toString())
        }

    }
}
