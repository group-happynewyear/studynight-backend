package kr.happynewyear.api.studynight.controller

import kr.happynewyear.studynight.application.service.InvitationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
@RequestMapping("/invitations")
class InvitationView(
    private val invitationService: InvitationService
) {

    @GetMapping("/{invitationId}/accept")
    fun accept(
        @PathVariable invitationId: UUID,
        model: Model
    ): String {
        val invitation = invitationService.confirm(invitationId, true)
        model.addAttribute("study", invitation.match.study)
        return "view/accept"
    }

    @GetMapping("/{invitationId}/reject")
    fun reject(
        @PathVariable invitationId: UUID,
        model: Model
    ): String {
        val invitation = invitationService.confirm(invitationId, false)
        model.addAttribute("study", invitation.match.study)
        return "view/reject"
    }
}
