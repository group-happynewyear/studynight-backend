package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.InvitationResult

data class InvitationResponse(
    val id: String,
    val match: Match,
    val student: Student
) {
    companion object {
        fun from(invitation: InvitationResult): InvitationResponse {
            return InvitationResponse(
                invitation.id.toString(),
                Match(invitation.match.id.toString(), Match.Study(invitation.match.study.id.toString())),
                Student(invitation.student.id.toString())
            )
        }
    }

    data class Match(
        val id: String,
        val study: Study
    ) {

        data class Study(
            val id: String
        )
    }

    data class Student(
        val id: String
    )

}
