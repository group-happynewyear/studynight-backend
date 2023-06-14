package kr.happynewyear.studynight.domain.repository

import kr.happynewyear.studynight.domain.model.Invitation
import kr.happynewyear.studynight.domain.model.InvitationState
import java.time.LocalDateTime
import java.util.*

interface InvitationRepository {

    fun findById(invitationId: UUID): Invitation?
    fun deleteByStateAndCreatedAtBefore(state: InvitationState, threshold: LocalDateTime)

}
