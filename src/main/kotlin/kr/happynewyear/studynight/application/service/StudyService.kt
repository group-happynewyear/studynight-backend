package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.StudyResult
import kr.happynewyear.studynight.application.exception.ResourceNotFoundException
import kr.happynewyear.studynight.application.exception.StudentNotFoundException
import kr.happynewyear.studynight.application.exception.StudyNotFoundException
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.domain.model.Study
import kr.happynewyear.studynight.domain.model.StudyContact
import kr.happynewyear.studynight.domain.repository.StudentRepository
import kr.happynewyear.studynight.domain.repository.StudyRepository
import kr.happynewyear.studynight.type.MatchParameter
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
        title: String, description: String, contactType: ContactType, contactAddress: String,
        condition: MatchParameter
    ): StudyResult {
        val student = studentRepository.findByUserId(userId) ?: throw StudentNotFoundException()
        val contact = StudyContact(contactType, contactAddress)
        val study = Study.create(
            student,
            title, description, contact,
            condition
        )
        studyRepository.save(study)
        return StudyResult.from(study)
    }


    fun list(userId: UUID): List<StudyResult> {
        val student = studentRepository.findByUserId(userId)!!
        return student.studies.map { StudyResult.from(it) }
    }

    fun get(studyId: UUID): StudyResult {
        val study = studyRepository.findById(studyId) ?: throw ResourceNotFoundException()
        return StudyResult.from(study)
    }


    @Transactional
    fun update(
        studyId: UUID, userId: UUID,
        title: String, description: String, contactType: ContactType, contactAddress: String,
        condition: MatchParameter
    ) {
        val study = studyRepository.findById(studyId) ?: throw StudyNotFoundException()
        val contact = StudyContact(contactType, contactAddress)
        study.update(title, description, contact, condition)
    }

}
