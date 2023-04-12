package kr.happynewyear.studynight.application.dto

import kr.happynewyear.studynight.domain.model.Invitation
import java.util.*

data class InvitationResult(
    val id: UUID,
    val match: Match,
    val student: Student
) {

    companion object {
        fun from(invitation: Invitation): InvitationResult {
            return InvitationResult(
                invitation.id,
                Match(invitation.match.id, Match.Study(invitation.match.study.id)),
                Student(invitation.student.id)
            )
        }
    }

    data class Match(
        val id: UUID,
        val study: Study
    ) {

        data class Study(
            val id: UUID
        )
    }

    data class Student(
        val id: UUID
    )

}
