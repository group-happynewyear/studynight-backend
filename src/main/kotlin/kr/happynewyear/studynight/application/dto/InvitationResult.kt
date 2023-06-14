package kr.happynewyear.studynight.application.dto

import kr.happynewyear.studynight.domain.model.Invitation
import java.util.*

data class InvitationResult(
    val id: UUID,
    val match: Match,
    val student: Student
) {

    companion object {
        fun from(invitation: Invitation): InvitationResult = with(invitation) {
            return InvitationResult(
                id,
                Match(match.id, Match.Study(match.study.id, match.study.title, match.study.contact.address)),
                Student(student.id)
            )
        }
    }

    data class Match(
        val id: UUID,
        val study: Study
    ) {

        data class Study(
            val id: UUID,
            val title: String,
            val contact: String
        )
    }

    data class Student(
        val id: UUID
    )

}
