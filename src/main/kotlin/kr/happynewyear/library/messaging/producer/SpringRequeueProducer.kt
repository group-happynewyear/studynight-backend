package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.messaging.Message
import org.springframework.stereotype.Component

@Component
class SpringRequeueProducer : SpringProducer<Message>
