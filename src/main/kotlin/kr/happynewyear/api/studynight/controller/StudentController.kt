package kr.happynewyear.api.studynight.controller

import jakarta.validation.Valid
import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.studynight.application.service.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/students")
class StudentController(
    private val studentService: StudentService
) {

    @PostMapping
    fun create(@Valid @RequestBody req: StudentCreateRequest): ResponseEntity<Void> {
        val student = studentService.create()
        val location = "/api/students/${student.id}"
        return ResponseEntity.created(URI.create(location)).build()
    }

}
