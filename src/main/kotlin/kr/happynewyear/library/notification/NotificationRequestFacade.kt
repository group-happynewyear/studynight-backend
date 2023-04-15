package kr.happynewyear.library.notification

import org.springframework.stereotype.Component

@Component
class NotificationRequestFacade {

    fun alert(applicationName: String, exceptionSummary: String, stacktrace: List<String>) {
        TODO("impl")
    }

}
