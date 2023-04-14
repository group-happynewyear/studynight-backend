package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.MatchResult
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
    private val matchRepository: MatchRepository
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
        return MatchResult.from(match)
    }

    fun get(matchId: UUID): MatchResult {
        val match = matchRepository.findById(matchId)!!
        return MatchResult.from(match)
    }

}
