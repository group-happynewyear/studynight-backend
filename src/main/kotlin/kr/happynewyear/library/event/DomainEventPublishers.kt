package kr.happynewyear.library.event

import org.springframework.context.ApplicationEventPublisher

class DomainEventPublishers {
    companion object {

        lateinit var applicationEventPublisher: ApplicationEventPublisher


        fun publish(domainEvent: DomainEvent) {
            applicationEventPublisher.publishEvent(domainEvent)
        }

    }
}
