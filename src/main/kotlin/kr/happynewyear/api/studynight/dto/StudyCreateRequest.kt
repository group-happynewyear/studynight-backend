package kr.happynewyear.api.studynight.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.constant.condition.*

data class StudyCreateRequest(

    @field:NotBlank
    val title: String,

    @field:Size(max = 100)
    val description: String,


    @field:NotNull
    val contactType: ContactType,

    @field:NotBlank
    val contactAddress: String,


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
