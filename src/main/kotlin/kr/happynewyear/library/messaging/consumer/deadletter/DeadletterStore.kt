package kr.happynewyear.library.messaging.consumer.deadletter

interface DeadletterStore {

    fun put(deadletter: Deadletter): String
    fun pop(deadletterId: String): Deadletter?

}
