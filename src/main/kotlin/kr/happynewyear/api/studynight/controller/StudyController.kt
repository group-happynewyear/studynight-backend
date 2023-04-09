package kr.happynewyear.api.studynight.controller

import jakarta.validation.Valid
import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.api.studynight.dto.StudyListResponse
import kr.happynewyear.api.studynight.dto.StudyResponse
import kr.happynewyear.library.security.authentication.Authenticated
import kr.happynewyear.library.security.authentication.Principal
import kr.happynewyear.studynight.application.service.StudyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/studies")
class StudyController(
    private val studyService: StudyService
) {

    @PostMapping
    fun create(
        @Authenticated principal: Principal,
        @Valid @RequestBody req: StudyCreateRequest
    ): ResponseEntity<Void> {
        val study = studyService.create(
            principal.userId,
            req.title, req.description,
            req.contactType, req.contactAddress,
            req.condition
        )
        val location = "/api/studies/${study.id}"
        return ResponseEntity.created(URI.create(location)).build()
    }

    @GetMapping
    fun list(@Authenticated principal: Principal): ResponseEntity<StudyListResponse> {
        val studies = studyService.list(principal.userId)
        val res = StudyListResponse(studies.map { StudyResponse.from(it) })
        return ResponseEntity.ok(res)
    }

    @GetMapping("/{studyId}")
    fun get(@PathVariable studyId: UUID): ResponseEntity<StudyResponse> {
        val study = studyService.get(studyId)
        val res = StudyResponse.from(study)
        return ResponseEntity.ok(res)
    }

}
