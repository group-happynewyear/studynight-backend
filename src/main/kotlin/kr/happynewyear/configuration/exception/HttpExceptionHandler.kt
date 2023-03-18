package kr.happynewyear.configuration.exception

import kr.happynewyear.authentication.application.exception.AccountNotFoundException
import kr.happynewyear.authentication.application.exception.DuplicatedEmailException
import kr.happynewyear.authentication.application.exception.InvalidPasswordException
import kr.happynewyear.authentication.application.exception.RefreshTokenNotFoundException
import kr.happynewyear.library.exception.ErrorResponse
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class HttpExceptionHandler {

    @ExceptionHandler(
        AccountNotFoundException::class,
        InvalidPasswordException::class,
        RefreshTokenNotFoundException::class
    )
    fun onAuthentication(e: Exception): ResponseEntity<ErrorResponse> {
        val message = "잘못된 인증 요청입니다."
        return ResponseEntity.status(UNAUTHORIZED).body(ErrorResponse(message))
    }

    @ExceptionHandler
    fun on(e: DuplicatedEmailException): ResponseEntity<ErrorResponse> {
        val message = "이미 사용 중인 이메일입니다."
        return ResponseEntity.status(BAD_REQUEST).body(ErrorResponse(message))
    }


    @ExceptionHandler(
        HttpMessageNotReadableException::class,
        MethodArgumentNotValidException::class
    )
    fun onBadRequestByFramework(e: Exception): ResponseEntity<ErrorResponse> {
        val message = "요청 형식을 확인해주세요."
        return ResponseEntity.status(BAD_REQUEST).body(ErrorResponse(message))
    }

    @ExceptionHandler
    fun onNotExpected(e: Exception): ResponseEntity<ErrorResponse> {
        // TODO alert
        // TODO bug report email
        val message = "예상하지 못한 오류가 발생하였습니다."
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ErrorResponse(message))
    }

}
