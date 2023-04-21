package kr.happynewyear.notification.application.service

import kr.happynewyear.library.test.ApplicationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ApplicationServiceTest : ApplicationTest() {

    @Autowired
    private lateinit var applicationService: ApplicationService


    @Test
    fun alert() {
        val applicationName = "applicationName"
        val exception = RuntimeException("message")
        val exceptionType = exception.javaClass.simpleName
        val exceptionMessage = exception.message ?: "null"
        val stacktrace = exception.stackTrace.map { it.toString() }
        applicationService.alert(applicationName, exceptionType, exceptionMessage, stacktrace)
    }

}
