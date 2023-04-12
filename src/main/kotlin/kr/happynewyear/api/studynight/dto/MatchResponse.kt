package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.MatchResult
import kr.happynewyear.studynight.type.MatchParameter

data class MatchResponse(
    val id: String,
    val condition: MatchParameter,
    val study: Study,
    val invitations: List<Invitation>
) {

    companion object {
        fun from(match: MatchResult): MatchResponse {
            return MatchResponse(
                match.id.toString(),
                match.condition,
                Study(match.study.id.toString()),
                match.invitations.map { Invitation(it.id.toString(), Invitation.Student(it.student.id.toString())) }
            )
        }
    }


    data class Study(
        val id: String
    )

    data class Invitation(
        val id: String,
        val student: Student
    ) {

        data class Student(
            val id: String
        )
    }

}
