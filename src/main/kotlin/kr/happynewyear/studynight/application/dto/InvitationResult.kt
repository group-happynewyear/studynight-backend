package kr.happynewyear.studynight.application.dto

import kr.happynewyear.studynight.constant.ContactType

data class InvitationResult(
    val contactType: ContactType,
    val contactAddress: String
)
