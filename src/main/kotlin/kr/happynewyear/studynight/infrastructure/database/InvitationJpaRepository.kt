package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Invitation
import kr.happynewyear.studynight.domain.model.InvitationState
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.*

interface InvitationJpaRepository : JpaRepository<Invitation, UUID> {

    fun deleteByStateAndCreatedAtBefore(state: InvitationState, threshold: LocalDateTime)

}
