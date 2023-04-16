package kr.happynewyear.notification.application.client

interface MailSender {

    fun send(address: String, title: String, content: String)

}
