package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserJpaRepository : JpaRepository<User, UUID>
