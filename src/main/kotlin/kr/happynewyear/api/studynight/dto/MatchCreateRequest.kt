package kr.happynewyear.api.studynight.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import kr.happynewyear.studynight.type.MatchParameter

data class MatchCreateRequest(

    @field:NotBlank
    val studyId: String,

    @field:Valid
    val condition: MatchParameter,

    @field:Min(1L)
    @field:Max(10L)
    val count: Int

)
