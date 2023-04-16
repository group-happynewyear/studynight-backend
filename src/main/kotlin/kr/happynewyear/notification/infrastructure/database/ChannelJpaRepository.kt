package kr.happynewyear.notification.infrastructure.database

import kr.happynewyear.notification.constant.ChannelType
import kr.happynewyear.notification.constant.OwnerType
import kr.happynewyear.notification.domain.model.Channel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ChannelJpaRepository : JpaRepository<Channel, UUID> {

    fun findByOwnerTypeAndOwnerIdAndType(ownerType: OwnerType, ownerId: String, type: ChannelType): List<Channel>

}
