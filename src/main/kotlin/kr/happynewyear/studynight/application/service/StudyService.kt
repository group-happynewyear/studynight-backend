package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.StudyResult
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.domain.model.Study
import kr.happynewyear.studynight.domain.repository.StudentRepository
import kr.happynewyear.studynight.domain.repository.StudyRepository
import kr.happynewyear.studynight.type.StudyMatchCondition
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class StudyService(
    private val studyRepository: StudyRepository,
    private val studentRepository: StudentRepository
) {

    @Transactional
    fun create(
        userId: UUID,
        title: String, description: String,
        contactType: ContactType, contactAddress: String,
        condition: StudyMatchCondition // TODO use
    ): StudyResult {
        val student = studentRepository.findByUserId(userId)!!
        val study = Study.create(student, title, description, contactType, contactAddress)
        studyRepository.save(study)
        return StudyResult.from(study)
    }

    fun list(userId: UUID): List<StudyResult> {
        TODO("impl")
    }

    fun get(studyId: UUID): StudyResult {
        TODO("impl")
    }

}
