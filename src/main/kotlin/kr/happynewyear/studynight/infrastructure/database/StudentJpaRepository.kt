package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StudentJpaRepository : JpaRepository<Student, UUID> {

    fun existsByUserId(userId: UUID): Boolean
    fun findByUserId(userId: UUID): Student?
    fun save(student: Student)

}
