package kr.happynewyear.notification.domain.repository

import kr.happynewyear.notification.domain.model.Channel
import java.util.*

interface ChannelRepository {

    fun save(channel: Channel)

    fun findByApplication(applicationName: String): List<Channel>
    fun findByUser(userId: UUID): List<Channel>

}
