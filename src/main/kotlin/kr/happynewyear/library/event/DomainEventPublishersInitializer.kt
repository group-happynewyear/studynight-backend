package kr.happynewyear.library.event

import jakarta.annotation.PostConstruct
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class DomainEventPublishersInitializer(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @PostConstruct
    fun initialize() {
        DomainEventPublishers.applicationEventPublisher = applicationEventPublisher
    }

}
