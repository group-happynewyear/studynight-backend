package kr.happynewyear.studynight.application.service

import kr.happynewyear.library.notification.NotificationRequestFacade
import kr.happynewyear.studynight.application.dto.MatchResult
import kr.happynewyear.studynight.domain.model.Invitation
import kr.happynewyear.studynight.domain.model.Match
import kr.happynewyear.studynight.domain.repository.MatchRepository
import kr.happynewyear.studynight.domain.repository.StudentRepository
import kr.happynewyear.studynight.domain.repository.StudyRepository
import kr.happynewyear.studynight.type.MatchParameter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MatchService(
    private val studyRepository: StudyRepository,
    private val studentRepository: StudentRepository,
    private val matchRepository: MatchRepository,
    private val notificationRequestFacade: NotificationRequestFacade
) {

    @Transactional
    fun create(
        studyId: UUID,
        condition: MatchParameter
    ): MatchResult {
        val study = studyRepository.findById(studyId)!!

        val matched = studentRepository.searchByCondition(condition)
        val engaged = study.students.toSet()
        val targets = matched.filter { !engaged.contains(it) }

        val match = Match.create(study, condition, targets)
        matchRepository.save(match)
        val result = MatchResult.from(match)

        match.invitations.forEach { send(it) }

        return result
    }

    private fun send(invitation: Invitation) {
        val userId = invitation.student.userId.toString()
        val title = "${invitation.match.study.title}에서 당신에게 관심을 보입니다."
        val content = "" +
            "<a href=\"/api/invitations/${invitation.id}\">초대장</a>\n" +
            "<a href=\"/api/invitations/${invitation.id}/accept?userId=$userId\">대화수락</a>"
        notificationRequestFacade.mail(userId, title, content)
    }


    fun get(matchId: UUID): MatchResult {
        val match = matchRepository.findById(matchId)!!
        return MatchResult.from(match)
    }

}
