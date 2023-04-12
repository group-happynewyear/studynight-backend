package kr.happynewyear.studynight.type

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import kr.happynewyear.studynight.constant.condition.*

data class MatchSource(

    @field:NotEmpty
    val schedules: Set<Schedule>,

    @field:NotEmpty
    val regions: Set<Region>,

    @field:NotNull
    val experience: Experience,

    @field:NotNull
    val position: Position,

    @field:NotNull
    val intensity: Intensity,

    @field:NotNull
    val scale: Scale

)
