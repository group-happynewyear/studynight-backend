package kr.happynewyear.notification.message

import com.github.josh910830.portablemq.core.consumer.deadletter.Deadletter
import com.github.josh910830.portablemq.core.message.IdentifiableMessage
import kr.happynewyear.library.marshalling.json.JsonMarshallers
import java.util.stream.Collectors.joining

data class ApplicationNotificationRequest(
    val applicationName: String,
    val title: String,
    val content: String
) : IdentifiableMessage() {
    companion object {

        fun of(applicationName: String, exception: Exception): ApplicationNotificationRequest {
            val type = exception.javaClass.simpleName
            val message = exception.message ?: ""
            val stacktrace = exception.stackTrace.map { it.toString() }.toList()

            val title = "[$applicationName] $type: $message"
            val content = stacktrace.stream().collect(joining("\n"))
            return ApplicationNotificationRequest(applicationName, title, content)
        }

        fun of(applicationName: String, deadletter: Deadletter, redriveLink: String): ApplicationNotificationRequest {
            val topic = deadletter.topic
            val message = JsonMarshallers.write(deadletter.message)

            val title = "[$applicationName] Deadletter: $topic"
            val content = "$message\n$redriveLink"
            return ApplicationNotificationRequest(applicationName, title, content)
        }

    }
}
