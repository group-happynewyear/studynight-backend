package kr.happynewyear.library.messaging.consumer

import kr.happynewyear.library.messaging.BrokerType
import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(FUNCTION)
annotation class Consume(
    val consumerGroup: String, // TODO nullable and default application-name
    val brokerType: BrokerType,
    val alert: Boolean = true,
    val deadletter: Boolean = true,
    val consumptionLog: Boolean = true
)
