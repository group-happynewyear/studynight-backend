package kr.happynewyear.bff.web.dto.view

import kr.happynewyear.api.studynight.dto.StudentMyResponse.Transaction

data class StudentView(
    val id: String,
    val nickname: String,
    val email: String,
    val point: Int,
    val transactions: List<Transaction>
)
