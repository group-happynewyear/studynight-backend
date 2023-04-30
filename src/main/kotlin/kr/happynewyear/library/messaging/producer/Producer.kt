package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.messaging.Message

interface Producer<T : Message> {

    fun produce(message: T)

}
