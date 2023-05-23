package kr.happynewyear.notification.application.dto

import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.model.ChannelAddress

data class Notification(
    val address: ChannelAddress,
    val title: String,
    val content: String
) {
    val message get() = "$title\n\n$content"

    companion object {
        fun of(channel: Channel, title: String, content: String): Notification {
            return Notification(channel.address, title, content)
        }
    }
}
