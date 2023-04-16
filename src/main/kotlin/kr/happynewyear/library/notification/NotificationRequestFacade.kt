package kr.happynewyear.library.notification

import kr.happynewyear.library.message.Producer
import kr.happynewyear.library.notification.message.AlertRequestMessage
import org.springframework.stereotype.Component

@Component
class NotificationRequestFacade(
    private val producer: Producer
) {

    fun alert(applicationName: String, exceptionSummary: String, stacktrace: List<String>) {
        producer.produce(AlertRequestMessage(applicationName, exceptionSummary, stacktrace))
    }

}
