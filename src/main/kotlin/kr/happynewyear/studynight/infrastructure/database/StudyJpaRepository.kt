package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Study
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StudyJpaRepository : JpaRepository<Study, UUID> {
}