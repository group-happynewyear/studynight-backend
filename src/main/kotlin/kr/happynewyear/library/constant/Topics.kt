package kr.happynewyear.library.constant

interface Topics {

    interface Notification {
        companion object {
            const val CHANNEL_CREATE_REQUEST = "channel-create-request"

            const val USER_NOTIFICATION_REQUEST = "user-notification-request"
            const val APPLICATION_NOTIFICATION_REQUEST = "application-notification-request"
        }
    }

}
