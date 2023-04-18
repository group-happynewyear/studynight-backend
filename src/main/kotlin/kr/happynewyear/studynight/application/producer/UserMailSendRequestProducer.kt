package kr.happynewyear.studynight.application.producer

import kr.happynewyear.library.message.Producer
import kr.happynewyear.notification.message.UserMailSendRequest
import org.springframework.stereotype.Component

@Component
class UserMailSendRequestProducer : Producer<UserMailSendRequest>
