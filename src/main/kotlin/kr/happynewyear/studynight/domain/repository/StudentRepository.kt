package kr.happynewyear.studynight.domain.repository

import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.type.MatchParameter
import java.util.*

interface StudentRepository {

    fun existsByUserId(userId: UUID): Boolean
    fun findById(studentId: UUID): Student?
    fun findByUserId(userId: UUID): Student?
    fun save(student: Student)
    fun searchByCondition(condition: MatchParameter): List<Student>

}
