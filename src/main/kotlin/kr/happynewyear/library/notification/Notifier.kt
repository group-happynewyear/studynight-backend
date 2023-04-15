package kr.happynewyear.library.notification

interface Notifier {

    fun mail(address: String, title: String, content: String)
    fun mailAsync(address: String, title: String, content: String)

    fun slack(address: String, message: String)
    fun slackAsync(address: String, message: String)

}
