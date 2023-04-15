package kr.happynewyear.library.exception

import kr.happynewyear.library.notification.NotificationRequestFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class AlertSender(
    private val notificationRequestFacade: NotificationRequestFacade,
    @Value("\${spring.application.name}") private val applicationName: String,
) {

    @Async
    fun send(e: Exception) {
        val exceptionSummary = "${e.javaClass.simpleName}: ${e.message}"
        val stacktrace = e.stackTrace.map { it.toString() }.toList()
        notificationRequestFacade.alert(applicationName, exceptionSummary, stacktrace)
    }

}
