package kr.happynewyear.configuration.exception

import kr.happynewyear.library.exception.AlertSender
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExceptionConfiguration {

    @Bean
    fun alertSender(
        @Value("\${spring.application.name}") applicationName: String,
        applicationAlertSendRequestProducer: ApplicationAlertSendRequestProducer
    ): AlertSender {
        return object : AlertSender {
            override fun send(e: Exception) {
                val exceptionType = e.javaClass.simpleName
                val exceptionMessage = e.message ?: ""
                val stacktrace = e.stackTrace.map { it.toString() }.toList()
                val message = ApplicationAlertSendRequest(applicationName, exceptionType, exceptionMessage, stacktrace)
                applicationAlertSendRequestProducer.produce(message)
            }
        }
    }

}
