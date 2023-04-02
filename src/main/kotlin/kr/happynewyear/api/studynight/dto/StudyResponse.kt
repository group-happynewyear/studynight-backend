package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.StudyResult
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.constant.condition.*

data class StudyResponse(
    val id: String,

    val title: String,
    val description: String,

    val contactType: ContactType,
    val contactAddress: String,

    val schedule: Schedule,
    val region: Region,
    val experiences: Set<Experience>,
    val positions: Set<Position>,
    val intensity: Intensity,
    val scale: Scale
) {
    companion object {
        fun from(study: StudyResult): StudyResponse {
            return StudyResponse(
                study.id.toString(),
                study.title, study.description,
                study.contactType, study.contactAddress,
                study.schedule, study.region, study.experiences, study.positions, study.intensity, study.scale
            )
        }
    }
}
