package kr.happynewyear.library.constant

interface Topics {
    companion object {
        const val APPLICATION_ALERT_SEND_REQUEST = "application-alert-send-request"
        const val APPLICATION_DEADLETTER_SEND_REQUEST = "application-deadletter-send-request"
        const val USER_MAIL_CHANNEL_CREATE_REQUEST = "user-mail-channel-create-request"
        const val USER_MAIL_SEND_REQUEST = "user-mail-send-request"
    }
}
