package kr.happynewyear.api.studynight.controller

import jakarta.validation.Valid
import kr.happynewyear.api.studynight.dto.MatchCreateRequest
import kr.happynewyear.api.studynight.dto.MatchResponse
import kr.happynewyear.studynight.application.service.MatchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/matches")
class MatchController(
    private val matchService: MatchService
) {

    @PostMapping
    fun create(@Valid @RequestBody req: MatchCreateRequest): ResponseEntity<Void> {
        val match = matchService.create(UUID.fromString(req.studyId), req.condition)
        val location = "/api/matches/${match.id}"
        return ResponseEntity.created(URI.create(location)).build()
    }

    @GetMapping("/{matchId}")
    fun get(@PathVariable matchId: UUID): ResponseEntity<MatchResponse> {
        val match = matchService.get(matchId)
        val res = MatchResponse.from(match)
        return ResponseEntity.ok(res)
    }

}
