package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.InvitationResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class InvitationService {

    fun accept(invitationId: UUID) {
        TODO("impl")
    }

    fun get(invitationId: UUID): InvitationResult {
        TODO("impl")
    }

}
