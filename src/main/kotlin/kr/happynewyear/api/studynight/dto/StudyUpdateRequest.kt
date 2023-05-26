package kr.happynewyear.api.studynight.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.type.MatchParameter

data class StudyUpdateRequest(

    @field:NotBlank
    val title: String,

    @field:Size(max = 100)
    val description: String,


    @field:NotNull
    val contactType: ContactType,

    @field:NotBlank
    val contactAddress: String,


    @field:Valid
    val condition: MatchParameter

)
