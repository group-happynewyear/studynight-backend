package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.InvitationResult
import kr.happynewyear.studynight.application.exception.InvitationNotFoundException
import kr.happynewyear.studynight.domain.model.InvitationState.PENDING
import kr.happynewyear.studynight.domain.repository.InvitationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime.now
import java.util.*

@Service
@Transactional(readOnly = true)
class InvitationService(
    private val invitationRepository: InvitationRepository
) {

    @Transactional
    fun confirm(invitationId: UUID, accept: Boolean): InvitationResult {
        val invitation = invitationRepository.findById(invitationId) ?: throw InvitationNotFoundException()
        invitation.confirm(accept)
        return InvitationResult.from(invitation)
    }

    @Transactional
    fun clear() {
        invitationRepository.deleteByStateAndCreatedAtBefore(PENDING, now().minusDays(3L))
    }

}
