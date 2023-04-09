package kr.happynewyear.studynight.domain.repository

import kr.happynewyear.studynight.domain.model.Study
import java.util.*

interface StudyRepository {

    fun save(study: Study)
    fun findById(studyId: UUID): Study?

}
