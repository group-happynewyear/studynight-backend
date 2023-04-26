package kr.happynewyear.configuration.messaging

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DeadletterJpaRepository : JpaRepository<DeadletterEntity, UUID> {
}