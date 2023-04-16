package kr.happynewyear.library.notification.message

import kr.happynewyear.library.message.Message

data class MailRequestMessage(
    val userId: String,
    val title: String,
    val content: String
) : Message
