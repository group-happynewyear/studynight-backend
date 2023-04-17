package kr.happynewyear.library.marshalling.json

import com.fasterxml.jackson.databind.ObjectMapper

class JsonIO {
    companion object {

        lateinit var objectMapper: ObjectMapper


        fun write(obj: Any): String {
            return objectMapper.writeValueAsString(obj)
        }

        fun <T> read(json: String, type: Class<T>): T {
            return objectMapper.readValue(json, type)
        }

    }
}
