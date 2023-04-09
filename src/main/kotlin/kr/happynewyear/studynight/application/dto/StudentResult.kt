package kr.happynewyear.studynight.application.dto

import kr.happynewyear.studynight.domain.model.Student
import java.util.*

data class StudentResult(
    val id: UUID
) {

    companion object {
        fun from(student: Student): StudentResult {
            return StudentResult(student.id)
        }
    }

}
