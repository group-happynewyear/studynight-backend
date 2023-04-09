package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.EnumType.STRING
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.library.utility.JsonIO
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.constant.EngagementRole.MANAGER
import kr.happynewyear.studynight.domain.service.EngagementRegistrationService
import kr.happynewyear.studynight.type.StudyMatchCondition

@Entity
@Table(
    name = "studies"
)
class Study(
    title: String, description: String,
    contactType: ContactType, contactAddress: String,
    condition: String
) : Identifiable() {

    companion object {
        fun create(
            creator: Student,
            title: String, description: String,
            contactType: ContactType, contactAddress: String,
            condition: StudyMatchCondition
        ): Study {
            val study = Study(title, description, contactType, contactAddress, JsonIO.write(condition))
            EngagementRegistrationService.register(study, creator, MANAGER)
            return study
        }
    }


    @Column(
        name = "title",
        nullable = false, updatable = true, unique = false
    )
    val title: String = title

    @Column(
        name = "description",
        nullable = false, updatable = true, unique = false
    )
    val description: String = description

    @Enumerated(STRING)
    @Column(
        name = "contact_type",
        nullable = false, updatable = true, unique = false
    )
    val contactType: ContactType = contactType

    @Column(
        name = "contact_address",
        nullable = false, updatable = true, unique = false
    )
    val contactAddress: String = contactAddress

    @Column(
        name = "condition",
        nullable = false, updatable = true, unique = false
    )
    val condition: String = condition

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
