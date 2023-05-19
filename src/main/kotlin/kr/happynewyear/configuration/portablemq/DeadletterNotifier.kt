package kr.happynewyear.configuration.portablemq

import com.github.josh910830.portablemq.core.consumer.deadletter.Deadletter
import com.github.josh910830.portablemq.support.template.DeadletterLinkNotifier
import kr.happynewyear.application.producer.ApplicationDeadletterSendRequestProducer
import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.notification.message.ApplicationDeadletterSendRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DeadletterNotifier(
    private val applicationDeadletterSendRequestProducer: ApplicationDeadletterSendRequestProducer,
    @Value("\${deadletter.server-address}") override val serverAddress: String,
    @Value("\${spring.application.name}") private val applicationName: String,
) : DeadletterLinkNotifier() {

    override fun doNotify(deadletter: Deadletter, exception: Exception, redriveLink: String) {
        val message = ApplicationDeadletterSendRequest(
            applicationName, deadletter.topic,
            JsonMarshallers.write(deadletter.message), redriveLink
        )
        applicationDeadletterSendRequestProducer.produce(message)
    }

}
