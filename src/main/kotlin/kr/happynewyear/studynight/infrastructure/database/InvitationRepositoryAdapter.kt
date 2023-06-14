package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Invitation
import kr.happynewyear.studynight.domain.model.InvitationState
import kr.happynewyear.studynight.domain.repository.InvitationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class InvitationRepositoryAdapter(
    private val invitationJpaRepository: InvitationJpaRepository
) : InvitationRepository {

    override fun findById(invitationId: UUID): Invitation? {
        return invitationJpaRepository.findByIdOrNull(invitationId)
    }

    override fun deleteByStateAndCreatedAtBefore(state: InvitationState, threshold: LocalDateTime) {
        invitationJpaRepository.deleteByStateAndCreatedAtBefore(state, threshold)
    }

}
