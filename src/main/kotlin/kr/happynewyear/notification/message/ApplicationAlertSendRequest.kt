package kr.happynewyear.notification.message

import kr.happynewyear.library.messaging.Message

data class ApplicationAlertSendRequest(
    val applicationName: String,
    val exceptionType: String,
    val exceptionMessage: String,
    val stacktrace: List<String>
) : Message {
    companion object {
        fun of(applicationName: String, e: Exception): ApplicationAlertSendRequest {
            val exceptionType = e.javaClass.simpleName
            val exceptionMessage = e.message ?: ""
            val stacktrace = e.stackTrace.map { it.toString() }.toList()
            return ApplicationAlertSendRequest(applicationName, exceptionType, exceptionMessage, stacktrace)
        }
    }
}
