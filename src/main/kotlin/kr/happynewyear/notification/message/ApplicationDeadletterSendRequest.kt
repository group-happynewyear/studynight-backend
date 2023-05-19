package kr.happynewyear.notification.message

import com.github.josh910830.portablemq.core.message.IdentifiableMessage

data class ApplicationDeadletterSendRequest(
    val applicationName: String,
    val topic: String,
    val messageContent: String,
    val redriveLink: String
) : IdentifiableMessage()
