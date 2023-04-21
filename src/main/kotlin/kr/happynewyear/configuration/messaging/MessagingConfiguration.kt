package kr.happynewyear.configuration.messaging

import kr.happynewyear.library.messaging.producer.broker.BrokerProducer
import kr.happynewyear.library.messaging.producer.broker.SpringProducer
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfiguration {

    @Bean
    fun brokerProducer(applicationEventPublisher: ApplicationEventPublisher): BrokerProducer {
        return SpringProducer(applicationEventPublisher)
    }

}
