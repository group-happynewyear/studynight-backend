package kr.happynewyear.library.notification

import kr.happynewyear.library.message.Producer
import kr.happynewyear.library.notification.message.AlertRequestMessage
import kr.happynewyear.library.notification.message.ChannelRequestMessage
import kr.happynewyear.library.notification.message.MailRequestMessage
import org.springframework.stereotype.Component

@Component
class NotificationRequestFacade(
    private val producer: Producer
) {

    fun alert(applicationName: String, exceptionSummary: String, stacktrace: List<String>) {
        producer.produce(AlertRequestMessage(applicationName, exceptionSummary, stacktrace))
    }

    fun channel(userId: String, email: String) {
        producer.produce(ChannelRequestMessage(userId, email))
    }

    fun mail(userId: String, title: String, content: String) {
        producer.produce(MailRequestMessage(userId, title, content))
    }

}
