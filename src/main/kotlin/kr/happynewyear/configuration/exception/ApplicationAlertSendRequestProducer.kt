package kr.happynewyear.configuration.exception

import kr.happynewyear.library.messaging.producer.Producer
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.stereotype.Component

@Component
class ApplicationAlertSendRequestProducer : Producer<ApplicationAlertSendRequest>
