package kr.happynewyear.configuration.portablemq

import com.github.josh910830.portablemq.core.consumer.deadletter.Deadletter
import com.github.josh910830.portablemq.core.producer.PortableProducer
import com.github.josh910830.portablemq.support.template.DeadletterLinkNotifier
import kr.happynewyear.notification.message.ApplicationNotificationRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DeadletterNotifier(
    private val producer: PortableProducer<ApplicationNotificationRequest>,
    @Value("\${spring.application.name}") private val appName: String,
    @Value("\${deadletter.server-address}") override val serverAddress: String,
) : DeadletterLinkNotifier() {

    override fun doNotify(deadletter: Deadletter, exception: Exception, redriveLink: String) {
        val msg = ApplicationNotificationRequest.of(appName, deadletter, redriveLink)
        producer.produce(msg)
    }

}
