package kr.happynewyear.bff.web.dto.form

import kr.happynewyear.bff.web.dto.element.ConditionElement

data class BookingCreateForm(
    val studyId: String,
    val condition: List<ConditionElement>,
    val count: Int
)
