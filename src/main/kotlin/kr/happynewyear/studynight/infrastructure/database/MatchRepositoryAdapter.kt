package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Match
import kr.happynewyear.studynight.domain.repository.MatchRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MatchRepositoryAdapter(
    private val matchJpaRepository: MatchJpaRepository
) : MatchRepository {

    override fun save(match: Match) {
        matchJpaRepository.save(match)
    }

    override fun findById(matchId: UUID): Match? {
        return matchJpaRepository.findByIdOrNull(matchId)
    }

}
