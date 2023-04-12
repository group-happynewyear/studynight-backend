package kr.happynewyear.studynight.type

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import kr.happynewyear.studynight.constant.condition.*

data class MatchParameter(

    @field:NotNull
    val schedule: Schedule,

    @field:NotNull
    val region: Region,

    @field:NotEmpty
    val experiences: Set<Experience>,

    @field:NotEmpty
    val positions: Set<Position>,

    @field:NotNull
    val intensity: Intensity,

    @field:NotNull
    val scale: Scale

)
