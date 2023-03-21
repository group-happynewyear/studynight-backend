package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.StudyResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class StudyService(

) {

    fun create(): StudyResult {
        TODO("impl")
    }

    fun list(userId: UUID): List<StudyResult> {
        TODO("impl")
    }

    fun get(studyId: UUID): StudyResult {
        TODO("impl")
    }

}
