package kr.happynewyear.studynight.application.service

import com.github.josh910830.portablemq.core.producer.PortableProducer
import kr.happynewyear.notification.message.UserNotificationRequest
import kr.happynewyear.studynight.application.dto.MatchResult
import kr.happynewyear.studynight.application.dto.StudyResult
import kr.happynewyear.studynight.application.exception.StudentNotFoundException
import kr.happynewyear.studynight.application.exception.StudyNotFoundException
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.domain.model.Invitation
import kr.happynewyear.studynight.domain.model.Match
import kr.happynewyear.studynight.domain.repository.MatchRepository
import kr.happynewyear.studynight.domain.repository.StudentRepository
import kr.happynewyear.studynight.domain.repository.StudyRepository
import kr.happynewyear.studynight.type.MatchParameter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.util.*

@Service
@Transactional(readOnly = true)
class MatchService(
    private val studyRepository: StudyRepository,
    private val studentRepository: StudentRepository,
    private val matchRepository: MatchRepository,
    private val transactionService: TransactionService,
    private val templateEngine: TemplateEngine,
    private val userNotificationRequestProducer: PortableProducer<UserNotificationRequest>,
    @Value("\${studynight.server-address}") private val serverAddress: String
) {

    @Transactional
    fun create(
        userId: UUID,
        studyId: UUID,
        condition: MatchParameter,
        count: Int
    ): MatchResult {
        val student = studentRepository.findByUserId(userId) ?: throw StudentNotFoundException()
        val study = studyRepository.findById(studyId) ?: throw StudyNotFoundException()

        val candidates = studentRepository.searchByCondition(condition)
        val targets = candidates.filter { it.isInvitable(study) }.take(count)

        transactionService.payForMatch(student, targets.size)

        val match = Match.create(study, condition, targets)
        matchRepository.save(match)
        val result = MatchResult.from(match)

        match.invitations.forEach { send(it) }

        return result
    }

    private fun send(invitation: Invitation) = with(invitation) {
        assert(match.study.contact.type == ContactType.KAKAOTALK_OPENCHAT) // TODO discuss

        val title = "[스터디나잇] ${match.study.title}에서 당신에게 관심을 보입니다."

        val ctx = Context()
        ctx.setVariable("study", StudyResult.from(match.study))
        ctx.setVariable("accept", "$serverAddress/invitations/$id/accept")
        ctx.setVariable("reject", "$serverAddress/invitations/$id/reject")
        val content = templateEngine.process("mail/invitation", ctx)

        val message = UserNotificationRequest.of(student.userId, title, content)
        userNotificationRequestProducer.produce(message)
    }


    fun get(matchId: UUID): MatchResult {
        val match = matchRepository.findById(matchId)!!
        return MatchResult.from(match)
    }

}
