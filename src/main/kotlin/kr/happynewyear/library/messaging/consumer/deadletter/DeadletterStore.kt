package kr.happynewyear.library.messaging.consumer.deadletter

interface DeadletterStore {

    fun save(deadletter: Deadletter)

}
