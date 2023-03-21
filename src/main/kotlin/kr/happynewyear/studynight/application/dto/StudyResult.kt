package kr.happynewyear.studynight.application.dto

import kr.happynewyear.studynight.constant.*
import java.util.*

data class StudyResult(
    val id: UUID,

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
)
