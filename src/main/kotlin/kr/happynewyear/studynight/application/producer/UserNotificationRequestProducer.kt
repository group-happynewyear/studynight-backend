package kr.happynewyear.studynight.application.producer

import com.github.josh910830.portablemq.core.producer.Producer
import com.github.josh910830.portablemq.spring.producer.SpringProducer
import kr.happynewyear.library.constant.Topics.Notification.Companion.USER_NOTIFICATION_REQUEST
import kr.happynewyear.notification.message.UserNotificationRequest

@Producer(USER_NOTIFICATION_REQUEST)
class UserNotificationRequestProducer : SpringProducer<UserNotificationRequest>
