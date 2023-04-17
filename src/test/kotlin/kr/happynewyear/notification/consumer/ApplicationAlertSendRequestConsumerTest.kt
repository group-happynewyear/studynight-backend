package kr.happynewyear.notification.consumer

import kr.happynewyear.library.test.ConsumerTest
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ApplicationAlertSendRequestConsumerTest : ConsumerTest() {

    @Autowired
    lateinit var applicationAlertSendRequestConsumer: ApplicationAlertSendRequestConsumer


    @Test
    fun on() {
        val applicationName = "applicationName"
        val exception = RuntimeException("message")
        val exceptionType = exception.javaClass.simpleName
        val exceptionMessage = exception.message ?: "null"
        val stacktrace = exception.stackTrace.map { it.toString() }
        val message = ApplicationAlertSendRequest(applicationName, exceptionType, exceptionMessage, stacktrace)
        applicationAlertSendRequestConsumer.on(message)
    }

}
