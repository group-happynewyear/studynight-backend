package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.exception.InvitationNotFoundException
import kr.happynewyear.studynight.domain.repository.InvitationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class InvitationService(
    private val invitationRepository: InvitationRepository
) {

    @Transactional
    fun confirm(invitationId: UUID, accept: Boolean) {
        val invitation = invitationRepository.findById(invitationId) ?: throw InvitationNotFoundException()
        invitation.confirm(accept)
    }

}
