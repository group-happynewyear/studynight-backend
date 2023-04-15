package kr.happynewyear.notification.slack

interface SlackSender {

    fun send(address: String, message: String)

}