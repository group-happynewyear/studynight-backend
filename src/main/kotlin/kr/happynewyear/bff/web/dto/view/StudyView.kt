package kr.happynewyear.bff.web.dto.view

data class StudyView(
    val title: String,
    val introduction: String,
    val contactURL: String,
    val condition: List<String>,
    val role: String?
)
