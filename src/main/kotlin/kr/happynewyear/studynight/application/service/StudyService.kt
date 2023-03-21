package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.StudyResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class StudyService(

) {

    fun create(): StudyResult {
        TODO("impl")
    }

}
