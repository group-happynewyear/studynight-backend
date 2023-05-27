package kr.happynewyear.api.studynight.controller

import kr.happynewyear.studynight.application.service.InvitationService
import org.apache.tomcat.websocket.Constants.FOUND
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/invitations")
class InvitationController(
    private val invitationService: InvitationService,
    @Value("\${studynight.study-page-root}") private val studyPageRoot: String
) {

    @GetMapping("/{invitationId}/accept")
    fun accept(@PathVariable invitationId: UUID): ResponseEntity<Void> {
        val invitation = invitationService.confirm(invitationId, true)
        val location = "$studyPageRoot/${invitation.match.study.id}"
        return ResponseEntity.status(FOUND).location(URI.create(location)).build()
    }

    @GetMapping("/{invitationId}/reject")
    fun reject(@PathVariable invitationId: UUID): ResponseEntity<Void> {
        invitationService.confirm(invitationId, false)
        val location = studyPageRoot
        return ResponseEntity.status(FOUND).location(URI.create(location)).build()
    }

}
