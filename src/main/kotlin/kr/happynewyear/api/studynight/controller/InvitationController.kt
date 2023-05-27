package kr.happynewyear.api.studynight.controller

import kr.happynewyear.studynight.application.service.InvitationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/invitations")
class InvitationController(
    private val invitationService: InvitationService
) {

    @GetMapping("/{invitationId}/accept")
    fun accept(@PathVariable invitationId: UUID): ResponseEntity<Void> {
        return confirm(invitationId, true)
    }

    @GetMapping("/{invitationId}/reject")
    fun reject(@PathVariable invitationId: UUID): ResponseEntity<Void> {
        return confirm(invitationId, false)
    }

    private fun confirm(invitationId: UUID, accept: Boolean): ResponseEntity<Void> {
        invitationService.confirm(invitationId, accept)
        return ResponseEntity.ok().build()
    }

}
