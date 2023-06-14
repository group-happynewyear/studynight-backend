package kr.happynewyear.authentication.batch.task

import kr.happynewyear.studynight.application.service.InvitationService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.batch.repeat.RepeatStatus.FINISHED
import org.springframework.stereotype.Component

@Component
class InvitationClearTasklet(
    private val invitationService: InvitationService
) : Tasklet {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        invitationService.clear()
        return FINISHED
    }

}
