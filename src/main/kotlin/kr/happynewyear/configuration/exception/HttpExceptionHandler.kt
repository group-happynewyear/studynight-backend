package kr.happynewyear.configuration.exception

import kr.happynewyear.authentication.application.exception.AccountNotFoundException
import kr.happynewyear.authentication.application.exception.DuplicatedEmailException
import kr.happynewyear.authentication.application.exception.InvalidPasswordException
import kr.happynewyear.authentication.application.exception.RefreshTokenNotFoundException
import kr.happynewyear.library.exception.ExceptionNotifier
import kr.happynewyear.library.exception.http.ErrorResponse
import kr.happynewyear.library.exception.http.ErrorResponseEntityFactories
import kr.happynewyear.studynight.application.exception.*
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class HttpExceptionHandler(
    private val exceptionNotifier: ExceptionNotifier
) {

    @ExceptionHandler(
        AccountNotFoundException::class,
        InvalidPasswordException::class,
        RefreshTokenNotFoundException::class
    )
    fun onAuthentication(e: Exception): ResponseEntity<ErrorResponse> {
        val message = "잘못된 인증 요청입니다."
        return ErrorResponseEntityFactories.create(UNAUTHORIZED, message)
    }

    @ExceptionHandler
    fun on(e: DuplicatedEmailException): ResponseEntity<ErrorResponse> {
        val message = "이미 사용 중인 이메일입니다."
        return ErrorResponseEntityFactories.create(BAD_REQUEST, message)
    }


    @ExceptionHandler
    fun on(e: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        val message = "요청하신 정보를 찾을 수 없습니다."
        return ErrorResponseEntityFactories.create(BAD_REQUEST, message)
    }

    @ExceptionHandler
    fun on(e: DuplicatedStudentException): ResponseEntity<ErrorResponse> {
        val message = "이미 해당 계정의 학생이 등록되어 있습니다."
        return ErrorResponseEntityFactories.create(BAD_REQUEST, message)
    }

    @ExceptionHandler
    fun on(e: StudentNotFoundException): ResponseEntity<ErrorResponse> {
        val message = "학생이 등록되어 있지 않습니다."
        return ErrorResponseEntityFactories.create(BAD_REQUEST, message)
    }

    @ExceptionHandler
    fun on(e: StudyNotFoundException): ResponseEntity<ErrorResponse> {
        val message = "스터디가 등록되어 있지 않습니다."
        return ErrorResponseEntityFactories.create(BAD_REQUEST, message)
    }

    @ExceptionHandler
    fun on(e: InvitationNotFoundException): ResponseEntity<ErrorResponse> {
        val message = "초대가 존재하지 않습니다."
        return ErrorResponseEntityFactories.create(BAD_REQUEST, message)
    }


    @ExceptionHandler(
        HttpMessageNotReadableException::class,
        MethodArgumentNotValidException::class
    )
    fun onBadRequestByFramework(e: Exception): ResponseEntity<ErrorResponse> {
        val message = "요청 형식을 확인해주세요."
        return ErrorResponseEntityFactories.create(BAD_REQUEST, message)
    }

    @ExceptionHandler
    fun onNotExpected(e: Exception): ResponseEntity<ErrorResponse> {
        exceptionNotifier.send(e)

        val message = "예상하지 못한 오류가 발생하였습니다." // TODO bug report email
        return ErrorResponseEntityFactories.create(INTERNAL_SERVER_ERROR, message)
    }

}
