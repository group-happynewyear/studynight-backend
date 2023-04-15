package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Invitation
import kr.happynewyear.studynight.domain.repository.InvitationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class InvitationRepositoryAdapter(
    private val invitationJpaRepository: InvitationJpaRepository
) : InvitationRepository {

    override fun findById(invitationId: UUID): Invitation? {
        return invitationJpaRepository.findByIdOrNull(invitationId)
    }

}
