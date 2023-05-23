package kr.happynewyear.notification.domain.model

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable

@Entity
@Table(
    name = "channels"
)
class Channel(
    owner: ChannelOwner,
    address: ChannelAddress
) : Identifiable() {

    @Embedded
    private val owner: ChannelOwner = owner

    @Embedded
    val address: ChannelAddress = address

}
