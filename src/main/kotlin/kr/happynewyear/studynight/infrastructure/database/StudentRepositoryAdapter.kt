package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.repository.StudentRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class StudentRepositoryAdapter(
    private val studentJpaRepository: StudentJpaRepository
) : StudentRepository {

    override fun existsByUserId(userId: UUID): Boolean {
        return studentJpaRepository.existsByUserId(userId)
    }

    override fun findByUserId(userId: UUID): Student? {
        return studentJpaRepository.findByUserId(userId)
    }

    override fun save(student: Student) {
        studentJpaRepository.save(student)
    }

}
