package kr.happynewyear.bff.web.dto.view

import kr.happynewyear.bff.web.dto.element.StudySummary

data class StudyListView(
    val holdList: List<StudySummary>,
    val joinList: List<StudySummary>
)
