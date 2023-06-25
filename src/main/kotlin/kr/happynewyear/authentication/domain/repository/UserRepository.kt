package kr.happynewyear.authentication.domain.repository

import kr.happynewyear.authentication.domain.model.User
import java.util.UUID

interface UserRepository {

    fun findById(userId:UUID) : User?

}