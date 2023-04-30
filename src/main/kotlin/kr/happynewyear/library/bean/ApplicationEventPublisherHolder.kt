package kr.happynewyear.library.bean

import jakarta.annotation.PostConstruct
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class ApplicationEventPublisherHolder(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @PostConstruct
    fun initialize() {
        instance = applicationEventPublisher
    }

    companion object {

        private lateinit var instance: ApplicationEventPublisher

        fun get(): ApplicationEventPublisher {
            return instance
        }

    }

}
