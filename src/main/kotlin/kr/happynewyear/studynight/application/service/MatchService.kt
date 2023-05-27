package kr.happynewyear.studynight.application.service

import com.github.josh910830.portablemq.core.producer.PortableProducer
import kr.happynewyear.notification.message.UserNotificationRequest
import kr.happynewyear.studynight.application.dto.MatchResult
import kr.happynewyear.studynight.domain.model.Invitation
import kr.happynewyear.studynight.domain.model.Match
import kr.happynewyear.studynight.domain.repository.MatchRepository
import kr.happynewyear.studynight.domain.repository.StudentRepository
import kr.happynewyear.studynight.domain.repository.StudyRepository
import kr.happynewyear.studynight.type.MatchParameter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MatchService(
    private val studyRepository: StudyRepository,
    private val studentRepository: StudentRepository,
    private val matchRepository: MatchRepository,
    private val userNotificationRequestProducer: PortableProducer<UserNotificationRequest>,
    @Value("\${studynight.server-address}") private val serverAddress: String,
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

    private fun send(invitation: Invitation) = with(invitation) {
        val userId = student.userId
        val title = "[스터디나잇] ${match.study.title}에서 당신에게 관심을 보입니다."
        // TODO mail as invitation
        val content = "" +
            "대화수락 : $serverAddress/api/invitations/$id/accept?userId=$userId"
        val message = UserNotificationRequest.of(userId, title, content)
        userNotificationRequestProducer.produce(message)
    }


    fun get(matchId: UUID): MatchResult {
        val match = matchRepository.findById(matchId)!!
        return MatchResult.from(match)
    }

}
