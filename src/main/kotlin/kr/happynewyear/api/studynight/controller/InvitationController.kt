package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.InvitationResponse
import kr.happynewyear.studynight.application.service.InvitationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/invitations")
class InvitationController(
    private val invitationService: InvitationService
) {

    @GetMapping("/accept") // TODO PUT
    fun accept(@RequestParam invitationId: UUID): ResponseEntity<Void> {
        invitationService.accept(invitationId)
        val location = "/api/invitations/$invitationId"
        return ResponseEntity.noContent().location(URI.create(location)).build()
    }

    @GetMapping("/{invitationId}")
    fun get(@PathVariable invitationId: UUID): ResponseEntity<InvitationResponse> {
        val invitation = invitationService.get(invitationId)
        val res = InvitationResponse.from(invitation)
        return ResponseEntity.ok(res)
    }

}
