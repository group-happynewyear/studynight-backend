package kr.happynewyear.library.marshalling.json

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class JsonIOInitializer(
    private val objectMapper: ObjectMapper
) {

    @PostConstruct
    fun initJsonIO() {
        JsonIO.objectMapper = objectMapper
    }

}
