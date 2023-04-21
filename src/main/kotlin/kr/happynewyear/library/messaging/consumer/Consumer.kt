package kr.happynewyear.library.messaging.consumer

import kr.happynewyear.library.messaging.Message

interface Consumer<T : Message> {

    fun consume(message: T)

}
