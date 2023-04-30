package kr.happynewyear.application.producer

import kr.happynewyear.library.messaging.producer.SpringProducer
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.stereotype.Component

@Component
class ApplicationAlertSendRequestProducer : SpringProducer<ApplicationAlertSendRequest>
