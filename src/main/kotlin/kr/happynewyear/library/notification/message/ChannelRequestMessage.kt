package kr.happynewyear.library.notification.message

import kr.happynewyear.library.message.Message

data class ChannelRequestMessage(
    val userId: String,
    val email: String
) : Message
