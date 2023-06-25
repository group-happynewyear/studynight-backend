package kr.happynewyear.bff.web.dto.view

import kr.happynewyear.api.studynight.dto.StudentMyResponse.Transaction
import kr.happynewyear.bff.web.dto.element.ConditionElement

data class StudentView(
    val id: String,
    val nickname: String,
    val email: String,
    val condition: List<ConditionElement>,
    val point: Int,
    val transactions: List<Transaction>
)
