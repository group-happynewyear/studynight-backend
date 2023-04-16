package kr.happynewyear.notification.domain.repository

import kr.happynewyear.notification.domain.model.Channel

interface ChannelRepository {

    fun save(channel: Channel)
    fun findMailByUser(userId: String): List<Channel>

}
