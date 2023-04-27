package kr.happynewyear.library.messaging.consumer.deadletter

interface DeadletterStore {

    fun add(deadletter: Deadletter): String
    fun pop(deadletterId: String): Deadletter?

}
