package kr.happynewyear.studynight.domain.repository

import kr.happynewyear.studynight.domain.model.Match
import java.util.*

interface MatchRepository {

    fun save(match: Match)
    fun findById(matchId: UUID): Match?

}
