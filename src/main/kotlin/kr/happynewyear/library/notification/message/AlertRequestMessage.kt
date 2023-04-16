package kr.happynewyear.library.notification.message

import kr.happynewyear.library.message.Message

data class AlertRequestMessage(
    val applicationName: String,
    val exceptionSummary: String,
    val stacktrace: List<String>
) : Message
