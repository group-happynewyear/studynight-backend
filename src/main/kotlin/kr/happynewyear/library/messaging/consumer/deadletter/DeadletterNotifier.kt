package kr.happynewyear.library.messaging.consumer.deadletter

import kr.happynewyear.library.messaging.Message

interface DeadletterNotifier {

    fun send(deadletterId: String, originalMessage: Message)

}
