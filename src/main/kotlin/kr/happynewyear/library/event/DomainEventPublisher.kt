package kr.happynewyear.library.event

import org.springframework.context.ApplicationEventPublisher

class DomainEventPublisher {
    companion object {

        lateinit var applicationEventPublisher: ApplicationEventPublisher


        fun publish(domainEvent: DomainEvent) {
            applicationEventPublisher.publishEvent(domainEvent)
        }

    }
}
