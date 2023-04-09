package kr.happynewyear.studynight.type

import kr.happynewyear.studynight.constant.condition.*

data class StudyMatchCondition(

    val schedule: Schedule,
    val region: Region,
    val experiences: Set<Experience>,
    val positions: Set<Position>,
    val intensity: Intensity,
    val scale: Scale

)
