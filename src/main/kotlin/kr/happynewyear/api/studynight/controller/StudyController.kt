package kr.happynewyear.api.studynight.controller

import jakarta.validation.Valid
import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.studynight.application.service.StudyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/studies")
class StudyController(
    private val studyService: StudyService
) {

    @PostMapping
    fun create(@Valid @RequestBody req: StudyCreateRequest): ResponseEntity<Void> {
        val study = studyService.create()
        val location = "/api/studies/${study.id}"
        return ResponseEntity.created(URI.create(location)).build()
    }

}