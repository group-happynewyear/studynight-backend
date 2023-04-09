package kr.happynewyear.configuration.io

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import kr.happynewyear.library.utility.JsonIO
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
