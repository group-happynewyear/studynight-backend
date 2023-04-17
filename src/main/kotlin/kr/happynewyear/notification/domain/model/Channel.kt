package kr.happynewyear.notification.domain.model

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.notification.constant.ChannelType
import kr.happynewyear.notification.constant.ChannelType.MAIL
import kr.happynewyear.notification.constant.OwnerType
import kr.happynewyear.notification.constant.OwnerType.APPLICATION
import kr.happynewyear.notification.constant.OwnerType.USER
import java.util.*

@Entity
@Table(
    name = "channels"
)
class Channel(
    ownerType: OwnerType, ownerId: String,
    type: ChannelType, address: String
) : Identifiable() {

    companion object {
        fun ofDefaultAlert(type: ChannelType, address: String): List<Channel> {
            return listOf(Channel(APPLICATION, "ALERT", type, address))
        }

        fun ofUserMail(userId: UUID, email: String): Channel {
            return Channel(USER, userId.toString(), MAIL, email)
        }
    }

    // TODO embed
    @Enumerated(STRING)
    @Column(
        name = "owner_type",
        nullable = false, updatable = false, unique = false
    )
    private val ownerType: OwnerType = ownerType

    @Column(
        name = "owner_id",
        nullable = false, updatable = false, unique = false
    )
    private val ownerId: String = ownerId

    @Enumerated(STRING)
    @Column(
        name = "type",
        nullable = false, updatable = false, unique = false
    )
    val type: ChannelType = type

    @Column(
        name = "address",
        nullable = false, updatable = false, unique = false
    )
    val address: String = address

}
