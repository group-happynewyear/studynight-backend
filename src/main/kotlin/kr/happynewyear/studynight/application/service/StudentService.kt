package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.StudentResult
import kr.happynewyear.studynight.application.exception.DuplicatedStudentException
import kr.happynewyear.studynight.application.exception.ResourceNotFoundException
import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.repository.StudentRepository
import kr.happynewyear.studynight.type.MatchSource
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
        condition: MatchSource
    ): StudentResult {
        if (isExist(userId)) throw DuplicatedStudentException()

        val student = Student.create(userId, nickname, condition)
        studentRepository.save(student)
        return StudentResult.from(student)
    }

    fun me(userId: UUID): StudentResult {
        val student = studentRepository.findByUserId(userId) ?: throw ResourceNotFoundException()
        return StudentResult.from(student)
    }

    @Transactional
    fun update(
        userId: UUID,
        nickname: String,
        condition: MatchSource
    ) {
        val student = studentRepository.findByUserId(userId) ?: throw ResourceNotFoundException()
        student.update(nickname, condition)
    }

}
