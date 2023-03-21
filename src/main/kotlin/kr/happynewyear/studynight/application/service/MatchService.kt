package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.MatchResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MatchService(

) {

    fun create(): MatchResult {
        TODO("impl")
    }

    fun get(matchId: UUID): MatchResult {
        TODO("impl")
    }

}
