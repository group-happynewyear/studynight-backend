package kr.happynewyear.api.studynight.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import kr.happynewyear.studynight.type.MatchSource

data class StudentCreateRequest(

    @field:NotBlank
    val nickname: String,

    @field:Valid
    val condition: MatchSource

)
