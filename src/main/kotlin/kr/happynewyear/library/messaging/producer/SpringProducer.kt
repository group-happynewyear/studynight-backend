package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.bean.ApplicationEventPublisherHolder
import kr.happynewyear.library.messaging.Message

interface SpringProducer<T : Message> : Producer<T> {

    override fun produce(message: T) {
        ApplicationEventPublisherHolder.get().publishEvent(message)
    }

}
