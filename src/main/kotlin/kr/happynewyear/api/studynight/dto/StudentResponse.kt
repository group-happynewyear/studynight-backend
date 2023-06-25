package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.StudentResult
import kr.happynewyear.studynight.domain.model.TransactionType
import kr.happynewyear.studynight.type.MatchSource
import java.time.LocalDateTime

data class StudentResponse(
    val nickname: String
) {
    companion object {
        fun from(student: StudentResult) = with(student) {
            StudentResponse(
                nickname
            )
        }
    }
}
