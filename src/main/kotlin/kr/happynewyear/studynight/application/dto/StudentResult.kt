package kr.happynewyear.studynight.application.dto

import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.type.MatchSource
import java.util.*

data class StudentResult(
    val id: UUID,
    val nickname: String,
    val condition: MatchSource
) {

    companion object {
        fun from(student: Student): StudentResult = with(student) {
            return StudentResult(id, nickname, condition)
        }
    }

}
