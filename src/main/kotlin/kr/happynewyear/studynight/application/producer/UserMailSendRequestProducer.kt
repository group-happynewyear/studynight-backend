package kr.happynewyear.studynight.application.producer

import com.github.josh910830.portablemq.core.producer.Producer
import com.github.josh910830.portablemq.spring.producer.SpringProducer
import kr.happynewyear.library.constant.Topics.Companion.USER_MAIL_SEND_REQUEST
import kr.happynewyear.notification.message.UserMailSendRequest

@Producer(USER_MAIL_SEND_REQUEST)
class UserMailSendRequestProducer : SpringProducer<UserMailSendRequest>
