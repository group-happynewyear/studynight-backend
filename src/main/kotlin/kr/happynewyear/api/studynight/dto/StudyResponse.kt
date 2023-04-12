package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.StudyResult
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.type.MatchParameter

data class StudyResponse(
    val id: String,

    val title: String,
    val description: String,

    val contactType: ContactType,
    val contactAddress: String,

    val condition: MatchParameter
) {
    companion object {
        fun from(study: StudyResult): StudyResponse {
            return StudyResponse(
                study.id.toString(),
                study.title, study.description,
                study.contactType, study.contactAddress,
                study.condition
            )
        }
    }
}
