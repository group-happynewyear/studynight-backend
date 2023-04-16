package kr.happynewyear.notification.application.dto

import kr.happynewyear.notification.constant.ChannelType

data class ChannelResult(
    val type: ChannelType,
    val address: String
)
