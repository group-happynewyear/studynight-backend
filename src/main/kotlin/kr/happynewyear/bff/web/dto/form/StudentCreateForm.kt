package kr.happynewyear.bff.web.dto.form

import kr.happynewyear.bff.web.dto.element.ConditionElement

data class StudentCreateForm(
    val nickName: String,
    val condition: List<ConditionElement>
)
