package kr.happynewyear.authentication.infrastructure.database

import kr.happynewyear.authentication.domain.model.User
import kr.happynewyear.authentication.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryAdapter(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {

    override fun findById(userId: UUID): User? {
        return userJpaRepository.findByIdOrNull(userId)
    }

}