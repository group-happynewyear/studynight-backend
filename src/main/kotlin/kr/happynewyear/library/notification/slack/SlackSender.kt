package kr.happynewyear.library.notification.slack

interface SlackSender {

    fun send(address: String, message: String)

}