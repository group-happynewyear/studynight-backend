package kr.happynewyear.studynight.application.dto

import kr.happynewyear.library.utility.JsonIO
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.domain.model.Study
import kr.happynewyear.studynight.type.StudyMatchCondition
import java.util.*

data class StudyResult(
    val id: UUID,

    val title: String,
    val description: String,

    val contactType: ContactType,
    val contactAddress: String,

    val condition: StudyMatchCondition
) {

    companion object {
        fun from(study: Study): StudyResult {
            return StudyResult(
                study.id,
                study.title, study.description,
                study.contactType, study.contactAddress,
                JsonIO.read(study.condition, StudyMatchCondition::class.java)
            )
        }
    }

}
