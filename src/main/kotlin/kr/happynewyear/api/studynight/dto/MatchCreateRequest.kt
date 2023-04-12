package kr.happynewyear.api.studynight.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import kr.happynewyear.studynight.type.MatchParameter

data class MatchCreateRequest(

    @field:NotBlank
    val studyId: String,

    @field:Valid
    val condition: MatchParameter

)
