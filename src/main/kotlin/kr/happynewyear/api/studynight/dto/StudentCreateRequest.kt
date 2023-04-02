package kr.happynewyear.api.studynight.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import kr.happynewyear.studynight.constant.condition.*

data class StudentCreateRequest(

    @field:NotBlank
    val nickname: String,


    @field:NotEmpty
    val schedules: Set<Schedule>,

    @field:NotEmpty
    val regions: Set<Region>,

    @field:NotNull
    val experience: Experience,

    @field:NotNull
    val position: Position,


    val intensity: Intensity?,

    val scale: Scale?

)
