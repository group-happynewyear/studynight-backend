package kr.happynewyear.configuration.messaging

import kr.happynewyear.application.producer.ApplicationDeadletterSendRequestProducer
import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.library.messaging.Message
import kr.happynewyear.library.messaging.consumer.deadletter.DeadletterNotifier
import kr.happynewyear.notification.message.ApplicationDeadletterSendRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DeadletterNotifier(
    private val applicationDeadletterSendRequestProducer: ApplicationDeadletterSendRequestProducer,
    @Value("\${spring.application.name}") private val applicationName: String
) : DeadletterNotifier {

    override fun send(deadletterId: String, originalMessage: Message) {
        val host = "http://localhost:8080" // TODO curr
        val path = "/admin/deadletters/requeue-token"
        val token = "t" // TODO issue

        val message = ApplicationDeadletterSendRequest(
            applicationName, originalMessage.javaClass.simpleName,
            JsonMarshallers.write(originalMessage), "$host$path?id=$deadletterId&token=$token"
        )
        applicationDeadletterSendRequestProducer.produce(message)
    }

}
