package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.StudentResult
import kr.happynewyear.studynight.type.MatchSource

data class StudentMyResponse(
    val id: String,
    val nickname: String,
    val condition: MatchSource
) {
    companion object {
        fun from(student: StudentResult): StudentMyResponse = with(student) {
            return StudentMyResponse(id.toString(), nickname, condition)
        }
    }
}
