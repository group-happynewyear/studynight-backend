package kr.happynewyear.configuration.event

import jakarta.annotation.PostConstruct
import kr.happynewyear.library.event.DomainEventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Configuration

@Configuration
class DomainEventPublisherInitializer(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @PostConstruct
    fun initDomainEventPublisher() {
        DomainEventPublisher.applicationEventPublisher = applicationEventPublisher
    }

}
