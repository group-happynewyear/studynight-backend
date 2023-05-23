package kr.happynewyear.configuration.exception

import com.github.josh910830.portablemq.core.producer.PortableProducer
import kr.happynewyear.library.exception.ExceptionNotifier
import kr.happynewyear.notification.message.ApplicationNotificationRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ExceptionNotifier(
    private val producer: PortableProducer<ApplicationNotificationRequest>,
    @Value("\${spring.application.name}") private val appName: String
) : ExceptionNotifier {

    override fun send(e: Exception) {
        val msg = ApplicationNotificationRequest.of(appName, e)
        producer.produce(msg)
    }

}
