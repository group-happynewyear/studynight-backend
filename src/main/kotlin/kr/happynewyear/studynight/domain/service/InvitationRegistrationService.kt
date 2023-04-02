package kr.happynewyear.studynight.domain.service

import kr.happynewyear.studynight.domain.model.Invitation
import kr.happynewyear.studynight.domain.model.Match
import kr.happynewyear.studynight.domain.model.Student

class InvitationRegistrationService {
    companion object {

        fun register(match: Match, student: Student) {
            val invitation = Invitation.create(match, student)
            match.add(invitation)
            student.add(invitation)
        }

    }
}
