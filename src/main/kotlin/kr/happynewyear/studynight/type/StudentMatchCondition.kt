package kr.happynewyear.studynight.type

import kr.happynewyear.studynight.constant.condition.*

data class StudentMatchCondition(

    val schedules: Set<Schedule>,
    val regions: Set<Region>,
    val experience: Experience,
    val position: Position,
    val intensity: Intensity?,
    val scale: Scale?

)
