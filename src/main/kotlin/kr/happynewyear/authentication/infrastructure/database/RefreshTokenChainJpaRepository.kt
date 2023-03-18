package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.RefreshTokenChain
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefreshTokenChainJpaRepository : JpaRepository<RefreshTokenChain, UUID>
