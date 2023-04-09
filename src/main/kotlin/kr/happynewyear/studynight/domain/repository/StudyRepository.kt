package kr.happynewyear.studynight.domain.repository

import kr.happynewyear.studynight.domain.model.Study

interface StudyRepository {

    fun save(study: Study)

}
