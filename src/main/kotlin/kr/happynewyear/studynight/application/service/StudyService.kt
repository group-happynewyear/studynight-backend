package kr.happynewyear.studynight.application.service

import kr.happynewyear.library.component.JsonIO
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
    private val studentRepository: StudentRepository,
    private val jsonIO: JsonIO
) {

    @Transactional
    fun create(
        userId: UUID,
        title: String, description: String,
        contactType: ContactType, contactAddress: String,
        condition: StudyMatchCondition
    ): StudyResult {
        val study = Study.create(
            studentRepository.findByUserId(userId)!!,
            title, description,
            contactType, contactAddress,
            jsonIO.write(condition)
        )
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
