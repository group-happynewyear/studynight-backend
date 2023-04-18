package kr.happynewyear.library.message

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class BrokerProducerHolderInitializer(
    private val brokerProducer: BrokerProducer
) {

    @PostConstruct
    fun initialize() {
        BrokerProducerHolder.brokerProducer = brokerProducer
    }

}
