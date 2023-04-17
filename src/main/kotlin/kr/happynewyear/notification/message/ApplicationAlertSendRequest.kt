package kr.happynewyear.notification.message

import kr.happynewyear.library.message.Message

data class ApplicationAlertSendRequest(
    val applicationName: String,
    val exceptionType: String,
    val exceptionMessage: String,
    val stacktrace: List<String>
) : Message
