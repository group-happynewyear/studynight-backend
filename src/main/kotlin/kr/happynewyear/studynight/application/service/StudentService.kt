package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.type.StudentMatchCondition
import kr.happynewyear.studynight.application.dto.StudentResult
import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class StudentService(
    private val studentRepository: StudentRepository
) {

    fun isExist(userId: UUID): Boolean {
        return studentRepository.existsByUserId(userId)
    }

    @Transactional
    fun create(
        userId: UUID,
        nickname: String,
        condition: StudentMatchCondition // TODO use
    ): StudentResult {
        val student = Student.create(userId, nickname)
        studentRepository.save(student)
        return StudentResult.from(student)
    }

}
