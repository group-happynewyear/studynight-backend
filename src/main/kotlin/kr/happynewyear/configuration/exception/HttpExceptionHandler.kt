package kr.happynewyear.configuration.exception

import kr.happynewyear.authentication.application.exception.RefreshTokenNotFoundException
import kr.happynewyear.library.exception.ErrorResponse
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class HttpExceptionHandler {

    @ExceptionHandler(
        HttpMessageNotReadableException::class,
        MethodArgumentNotValidException::class
    )
    fun onBadRequestByFramework(e: Exception): ResponseEntity<ErrorResponse> {
        val message = "요청 형식을 확인해주세요."
        val response = ErrorResponse(message)
        return ResponseEntity.status(BAD_REQUEST).body(response)
    }

    @ExceptionHandler(
        RefreshTokenNotFoundException::class
    )
    fun on(e: Exception): ResponseEntity<ErrorResponse> {
        val message = "리프레시 토큰을 찾을 수 없습니다."
        val response = ErrorResponse(message)
        return ResponseEntity.status(UNAUTHORIZED).body(response)
    }

}
