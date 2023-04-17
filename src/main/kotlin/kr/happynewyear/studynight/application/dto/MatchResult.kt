package kr.happynewyear.studynight.application.dto

import kr.happynewyear.library.marshalling.json.JsonIO
import kr.happynewyear.studynight.domain.model.Match
import kr.happynewyear.studynight.type.MatchParameter
import java.util.*

data class MatchResult(
    val id: UUID,
    val condition: MatchParameter,
    val study: Study,
    val invitations: List<Invitation>
) {

    companion object {
        fun from(match: Match): MatchResult {
            return MatchResult(
                match.id,
                JsonIO.read(match.condition, MatchParameter::class.java),
                Study(match.study.id),
                match.invitations.map { Invitation(it.id, Invitation.Student(it.student.id)) }
            )
        }
    }


    data class Study(
        val id: UUID
    )

    data class Invitation(
        val id: UUID,
        val student: Student
    ) {

        data class Student(
            val id: UUID
        )
    }

}
