package kr.happynewyear.studynight.domain.repository

import kr.happynewyear.studynight.domain.model.Student
import java.util.*

interface StudentRepository {

    fun existsByUserId(userId: UUID): Boolean
    fun save(student: Student)

}
