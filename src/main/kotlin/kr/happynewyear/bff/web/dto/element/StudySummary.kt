package kr.happynewyear.bff.web.dto.element

data class StudySummary(
    val title: String,
    val condition: List<String>,
    val createdAt: Long
)
