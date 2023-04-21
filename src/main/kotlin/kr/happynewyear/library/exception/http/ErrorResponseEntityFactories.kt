package kr.happynewyear.library.exception.http

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponseEntityFactories {
    companion object {

        fun create(status: HttpStatus, message: String): ResponseEntity<ErrorResponse> {
            return ResponseEntity.status(status).body(ErrorResponse(message))
        }

    }
}
