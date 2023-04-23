package kr.happynewyear.studynight.application.service

import kr.happynewyear.notification.message.UserMailSendRequest
import kr.happynewyear.studynight.application.dto.MatchResult
import kr.happynewyear.studynight.application.producer.UserMailSendRequestProducer
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
    private val userMailSendRequestProducer: UserMailSendRequestProducer
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
        val userId = invitation.student.userId
        val title = "${invitation.match.study.title}에서 당신에게 관심을 보입니다."
        val api = "http://localhost:8080" // TODO
        val content = "" +
            "초대장  : $api/api/invitations/${invitation.id}\n" +
            "대화수락 : $api/api/invitations/${invitation.id}/accept?userId=$userId"
        val message = UserMailSendRequest(userId, title, content)
        userMailSendRequestProducer.produce(message)
    }


    fun get(matchId: UUID): MatchResult {
        val match = matchRepository.findById(matchId)!!
        return MatchResult.from(match)
    }

}
