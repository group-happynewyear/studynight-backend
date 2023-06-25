package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.repository.StudentRepository
import kr.happynewyear.studynight.type.MatchParameter
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class StudentRepositoryAdapter(
    private val studentJpaRepository: StudentJpaRepository
) : StudentRepository {

    override fun existsByUserId(userId: UUID): Boolean {
        return studentJpaRepository.existsByUserId(userId)
    }

    override fun findById(studentId: UUID): Student? {
        return studentJpaRepository.findByIdOrNull(studentId)
    }

    override fun findByUserId(userId: UUID): Student? {
        return studentJpaRepository.findByUserId(userId)
    }

    override fun save(student: Student) {
        studentJpaRepository.save(student)
    }

    override fun searchByCondition(condition: MatchParameter): List<Student> {
        return studentJpaRepository.searchByConditions(
            condition.schedule.name,
            condition.region.name,
            condition.experiences.map { it.name },
            condition.positions.map { it.name },
            condition.intensity.name,
            condition.scale.name,
        )
    }

}
