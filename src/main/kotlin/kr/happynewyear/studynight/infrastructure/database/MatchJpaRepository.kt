package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Match
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MatchJpaRepository : JpaRepository<Match, UUID> {
}
