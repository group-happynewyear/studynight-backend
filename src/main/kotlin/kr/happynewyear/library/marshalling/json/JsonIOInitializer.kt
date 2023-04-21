package kr.happynewyear.library.marshalling.json

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class JsonIOInitializer(
    private val objectMapper: ObjectMapper
) {

    @PostConstruct
    fun initialize() {
        JsonIO.objectMapper = objectMapper
    }

}
