package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.InvitationResult
import kr.happynewyear.studynight.domain.repository.InvitationRepository
import kr.happynewyear.studynight.domain.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class InvitationService(
    private val invitationRepository: InvitationRepository,
    private val studentRepository: StudentRepository
) {

    fun get(invitationId: UUID): InvitationResult {
        val invitation = invitationRepository.findById(invitationId)!!
        return InvitationResult.from(invitation)
    }

    @Transactional
    fun confirm(userId: UUID, invitationId: UUID, accept: Boolean) {
        val invitation = invitationRepository.findById(invitationId)!!
        val student = studentRepository.findByUserId(userId)!!
        student.accept(invitation)
    }

}
