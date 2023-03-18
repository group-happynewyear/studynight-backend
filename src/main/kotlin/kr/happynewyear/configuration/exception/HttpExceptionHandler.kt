package kr.happynewyear.configuration.exception

import kr.happynewyear.authentication.application.exception.RefreshTokenNotFoundException
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class HttpExceptionHandler {

    @ExceptionHandler
    fun on(e: RefreshTokenNotFoundException): ResponseEntity<Void> {
        return ResponseEntity.status(UNAUTHORIZED).build() // TODO body
    }

}
