package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.domain.model.Study
import kr.happynewyear.studynight.domain.repository.StudyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class StudyRepositoryAdapter(
    private val studyJpaRepository: StudyJpaRepository
) : StudyRepository {

    override fun save(study: Study) {
        studyJpaRepository.save(study)
    }

    override fun findById(studyId: UUID): Study? {
        return studyJpaRepository.findByIdOrNull(studyId)
    }

}
