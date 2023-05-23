package kr.happynewyear.notification.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import kr.happynewyear.notification.constant.AddressType
import kr.happynewyear.notification.constant.AddressType.MAIL
import kr.happynewyear.notification.constant.AddressType.SLACK

@Embeddable
data class ChannelAddress(

    @Enumerated(STRING)
    @Column(
        name = "address_type",
        nullable = false, updatable = false, unique = false
    )
    val type: AddressType,

    @Column(
        name = "address_value",
        nullable = false, updatable = false, unique = false
    )
    val value: String

) {
    companion object {

        fun ofMail(address: String): ChannelAddress {
            return ChannelAddress(MAIL, address)
        }

        fun ofSlack(address: String): ChannelAddress {
            return ChannelAddress(SLACK, address)
        }

    }
}