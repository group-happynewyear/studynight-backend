package kr.happynewyear.bff.web.dto.form

import kr.happynewyear.bff.web.dto.element.ConditionElement

data class StudyUpdateForm(
    val title: String,
    val introduction: String,
    val contactURL: String,
    val condition: List<ConditionElement>
)
