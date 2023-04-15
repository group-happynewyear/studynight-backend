package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Invitation
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface InvitationJpaRepository : JpaRepository<Invitation, UUID> {
}
