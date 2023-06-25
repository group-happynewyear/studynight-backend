package kr.happynewyear.bff.web.dto.view

import java.time.LocalDateTime

data class StudyView(
    val title: String,
    val introduction: String,
    val contactURL: String,
    val condition: List<String>,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val role: String?
)
