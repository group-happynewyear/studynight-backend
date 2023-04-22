package kr.happynewyear.library.utility

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class Dates {
    companion object {

        fun plusMinutes(date: Date, minutes: Long): Date {
            return Date(date.time + minutes * 60 * 1000)
        }

        fun now(): Date {
            return Date()
        }

        fun ofAfterMinutes(minutes: Long): Date {
            return plusMinutes(now(), minutes)
        }

        fun from(localDateTime: LocalDateTime): Date {
            val zoneId = ZoneId.systemDefault()
            val instant = localDateTime.atZone(zoneId).toInstant()
            return Date.from(instant)
        }

    }
}
