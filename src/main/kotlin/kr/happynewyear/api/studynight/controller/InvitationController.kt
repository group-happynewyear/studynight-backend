package kr.happynewyear.api.studynight.controller

import kr.happynewyear.api.studynight.dto.InvitationResponse
import kr.happynewyear.library.security.authentication.Authenticated
import kr.happynewyear.library.security.authentication.Principal
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

    @GetMapping("/{invitationId}")
    fun get(@PathVariable invitationId: UUID): ResponseEntity<InvitationResponse> {
        val invitation = invitationService.get(invitationId)
        val res = InvitationResponse.from(invitation)
        return ResponseEntity.ok(res)
    }

    @PutMapping("/{invitationId}")
    fun confirm(
        @PathVariable invitationId: UUID, @RequestParam accept: Boolean,
        @Authenticated principal: Principal
    ): ResponseEntity<Void> {
        invitationService.confirm(principal.userId, invitationId, accept)
        val location = "/api/invitations/$invitationId"
        return ResponseEntity.noContent().location(URI.create(location)).build()
    }

    @GetMapping("/{invitationId}/accept")
    fun accept(@PathVariable invitationId: UUID, @RequestParam userId: UUID): ResponseEntity<Void> {
        return confirm(invitationId, true, Principal(userId))
    }

}
