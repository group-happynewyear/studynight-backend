package kr.happynewyear.library.component

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class JsonIO(
    private val objectMapper: ObjectMapper
) {

    fun write(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }

    fun <T> read(json: String, type: Class<T>): T {
        return objectMapper.readValue(json, type)
    }

}
