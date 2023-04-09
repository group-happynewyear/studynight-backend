package kr.happynewyear.api.studynight.controller

import jakarta.validation.Valid
import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.api.studynight.dto.StudentExistResponse
import kr.happynewyear.library.security.authentication.Authenticated
import kr.happynewyear.library.security.authentication.Principal
import kr.happynewyear.studynight.application.service.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/students")
class StudentController(
    private val studentService: StudentService
) {

    @GetMapping("/me/is_exists")
    fun isExist(@Authenticated principal: Principal): ResponseEntity<StudentExistResponse> {
        val isExist = studentService.isExist(principal.userId)
        val res = StudentExistResponse(isExist)
        return ResponseEntity.ok(res)
    }

    @PostMapping
    fun create(@Valid @RequestBody req: StudentCreateRequest): ResponseEntity<Void> {
        val student = studentService.create()
        val location = "/api/students/${student.id}"
        return ResponseEntity.created(URI.create(location)).build()
    }

}
