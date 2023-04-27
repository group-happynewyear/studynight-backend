package kr.happynewyear.library.messaging.consumer.deadletter

interface DeadletterStore {

    fun save(deadletter: Deadletter): String
    fun findById(deadletterId: String): Deadletter?

}
