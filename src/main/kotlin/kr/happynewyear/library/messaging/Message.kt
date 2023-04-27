package kr.happynewyear.library.messaging

import kr.happynewyear.library.utility.Randoms

abstract class Message {
    val id = Randoms.uuidString()
}
