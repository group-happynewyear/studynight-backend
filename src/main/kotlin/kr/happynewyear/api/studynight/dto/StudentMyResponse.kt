package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.StudentResult

data class StudentMyResponse(
    val id: String
) {
    companion object {
        fun from(student: StudentResult): StudentMyResponse {
            return StudentMyResponse(student.id.toString())
        }
    }
}
