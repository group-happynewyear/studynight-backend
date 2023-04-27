package kr.happynewyear.notification.message

import kr.happynewyear.library.messaging.Message

data class ApplicationDeadletterSendRequest(
    val applicationName: String,
    val messageType: String,
    val messageContent: String,
    val requeueLink: String
) : Message()
