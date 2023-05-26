package kr.happynewyear.api.studynight.controller

import jakarta.validation.Valid
import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.api.studynight.dto.StudentExistResponse
import kr.happynewyear.api.studynight.dto.StudentMyResponse
import kr.happynewyear.api.studynight.dto.StudentUpdateRequest
import kr.happynewyear.library.security.authentication.Authenticated
import kr.happynewyear.library.security.authentication.Principal
import kr.happynewyear.studynight.application.service.StudentService
import kr.happynewyear.studynight.constant.condition.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/students")
class StudentController(
    private val studentService: StudentService
) {

    @GetMapping("/me/is_exist")
    fun isExist(@Authenticated principal: Principal): ResponseEntity<StudentExistResponse> {
        val isExist = studentService.isExist(principal.userId)
        val res = StudentExistResponse(isExist)
        return ResponseEntity.ok(res)
    }

    @PostMapping
    fun create(
        @Authenticated principal: Principal,
        @Valid @RequestBody req: StudentCreateRequest
    ): ResponseEntity<Void> {
        val student = studentService.create(
            principal.userId, req.nickname,
            req.condition
        )
        val location = "/api/students/${student.id}"
        return ResponseEntity.created(URI.create(location)).build()
    }

    @GetMapping("/me")
    fun me(@Authenticated principal: Principal): ResponseEntity<StudentMyResponse> {
        val student = studentService.me(principal.userId)
        val res = StudentMyResponse.from(student)
        return ResponseEntity.ok(res)
    }

    @PutMapping("/me")
    fun update(
        @Authenticated principal: Principal,
        @Valid @RequestBody req: StudentUpdateRequest
    ): ResponseEntity<Void> {
        studentService.update(
            principal.userId, req.nickname,
            req.condition
        )
        return ResponseEntity.noContent().build()
    }

}
