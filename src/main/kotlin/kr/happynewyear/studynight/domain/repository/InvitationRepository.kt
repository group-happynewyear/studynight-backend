package kr.happynewyear.studynight.domain.repository

import kr.happynewyear.studynight.domain.model.Invitation
import java.util.*

interface InvitationRepository {

    fun findById(invitationId: UUID): Invitation?

}
