package kr.happynewyear.studynight.domain.model

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.constant.EngagementRole.MANAGER
import kr.happynewyear.studynight.domain.service.EngagementRegistrationService

@Entity
@Table(
    name = "studies"
)
class Study(

) : Identifiable() {

    companion object {
        fun create(creator: Student): Study {
            val study = Study()
            EngagementRegistrationService.register(study, creator, MANAGER)
            return study
        }
    }


    @OneToMany(
        mappedBy = "study",
        cascade = [ALL], orphanRemoval = true
    )
    private val _engagements: MutableList<Engagement> = mutableListOf()

    @OneToMany(
        mappedBy = "study",
        cascade = [ALL], orphanRemoval = true
    )
    private val _matches: MutableList<Match> = mutableListOf()


    fun add(engagement: Engagement) {
        _engagements.add(engagement)
    }

    fun add(match: Match) {
        _matches.add(match)
    }

}
