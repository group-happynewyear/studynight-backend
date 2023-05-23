package kr.happynewyear.authentication.application.producer

import com.github.josh910830.portablemq.core.producer.Producer
import com.github.josh910830.portablemq.spring.producer.SpringProducer
import kr.happynewyear.library.constant.Topics.Notification.Companion.CHANNEL_CREATE_REQUEST
import kr.happynewyear.notification.message.ChannelCreateRequest

@Producer(CHANNEL_CREATE_REQUEST)
class ChannelCreateRequestProducer : SpringProducer<ChannelCreateRequest>
