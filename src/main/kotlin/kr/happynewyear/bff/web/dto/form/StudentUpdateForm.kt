package kr.happynewyear.bff.web.dto.form

import kr.happynewyear.bff.web.dto.element.ConditionElement

data class StudentUpdateForm(
    val nickName: String,
    val condition: List<ConditionElement>
)
