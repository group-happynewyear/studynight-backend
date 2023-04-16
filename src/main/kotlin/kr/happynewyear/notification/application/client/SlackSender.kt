package kr.happynewyear.notification.application.client

interface SlackSender {

    fun send(address: String, message: String)

}