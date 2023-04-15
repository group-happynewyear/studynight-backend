package kr.happynewyear.notification.mail

interface MailSender {

    fun send(address: String, title: String, content: String)

}
