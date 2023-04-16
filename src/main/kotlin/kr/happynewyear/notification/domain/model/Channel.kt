package kr.happynewyear.notification.domain.model

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.notification.constant.ChannelType
import kr.happynewyear.notification.constant.OwnerType

@Entity
@Table(
    name = "channels"
)
class Channel(
    ownerType: OwnerType, ownerId: String,
    type: ChannelType, address: String
) : Identifiable() {

    companion object {
        fun create(
            ownerType: OwnerType, ownerId: String,
            type: ChannelType, address: String
        ): Channel {
            return Channel(ownerType, ownerId, type, address)
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
    private val type: ChannelType = type

    @Column(
        name = "address",
        nullable = false, updatable = false, unique = false
    )
    val address: String = address

}
