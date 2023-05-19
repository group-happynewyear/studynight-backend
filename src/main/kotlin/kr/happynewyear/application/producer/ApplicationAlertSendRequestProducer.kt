package kr.happynewyear.application.producer

import com.github.josh910830.portablemq.core.producer.Producer
import com.github.josh910830.portablemq.spring.producer.SpringProducer
import kr.happynewyear.library.constant.Topics.Companion.APPLICATION_ALERT_SEND_REQUEST
import kr.happynewyear.notification.message.ApplicationAlertSendRequest

@Producer(APPLICATION_ALERT_SEND_REQUEST)
class ApplicationAlertSendRequestProducer : SpringProducer<ApplicationAlertSendRequest>
