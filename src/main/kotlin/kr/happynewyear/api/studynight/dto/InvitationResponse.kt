package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.InvitationResult
import kr.happynewyear.studynight.constant.ContactType

data class InvitationResponse(
    val contactType: ContactType,
    val contactAddress: String
) {
    companion object {
        fun from(invitation: InvitationResult): InvitationResponse {
            return InvitationResponse(invitation.contactType, invitation.contactAddress)
        }
    }
}
