package kr.happynewyear.api.studynight.dto

import jakarta.validation.constraints.*
import kr.happynewyear.studynight.constant.*

data class MatchCreateRequest(

    @field:NotBlank
    val studyId: String,

    @field:Min(1)
    @field:Max(10)
    val count: Int,


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
