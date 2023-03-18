package kr.happynewyear.library.notification.mail

interface MailSender {

    fun send(address: String, title: String, content: String)

}
