package kr.happynewyear.library.utility

import java.util.*
import java.util.UUID.randomUUID

class Randoms {
    companion object {

        fun uuid(): UUID {
            return randomUUID()
        }

        fun uuidString(): String {
            return uuid().toString()
        }

        fun string(): String {
            return string(6)
        }

        fun string(length: Int): String {
            return uuidString().replace("-", "").substring(0, length)
        }

    }
}
