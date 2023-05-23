package kr.happynewyear.notification.infrastructure.database

import kr.happynewyear.notification.domain.model.Channel
import kr.happynewyear.notification.domain.model.ChannelOwner
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ChannelJpaRepository : JpaRepository<Channel, UUID> {

    fun findByOwner(owner: ChannelOwner): List<Channel>

}
