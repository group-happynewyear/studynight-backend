package kr.happynewyear.library.exception

import kr.happynewyear.library.message.Producer
import kr.happynewyear.notification.message.ApplicationAlertSendRequest
import org.springframework.stereotype.Component

@Component
class ApplicationAlertSendRequestProducer : Producer<ApplicationAlertSendRequest>
