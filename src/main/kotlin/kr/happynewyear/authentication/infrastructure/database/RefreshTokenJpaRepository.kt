package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefreshTokenJpaRepository : JpaRepository<RefreshToken, UUID> {
}