package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.EnumType.STRING
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.constant.EngagementRole.*
import kr.happynewyear.studynight.domain.service.EngagementRegistrationService
import kr.happynewyear.studynight.type.MatchParameter
import java.time.LocalDateTime

@Entity
@Table(
    name = "studies"
)
class Study(
    title: String, description: String,
    contactType: ContactType, contactAddress: String,
    condition: String,
    createdAt: LocalDateTime
) : Identifiable() {

    companion object {
        fun create(
            creator: Student,
            title: String, description: String,
            contactType: ContactType, contactAddress: String,
            condition: MatchParameter
        ): Study {
            val study = Study(
                title, description,
                contactType, contactAddress,
                JsonMarshallers.write(condition),
                LocalDateTime.now()
            )
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

    @Column(
        name = "createdAt",
        nullable = false, updatable = false, unique = false
    )
    val createdAt: LocalDateTime = createdAt

    @OneToMany(
        mappedBy = "study",
        cascade = [ALL], orphanRemoval = true
    )
    private val _engagements: MutableList<Engagement> = mutableListOf()
    val students: List<Student> get() = _engagements.map { it.student }
    val managers: List<Student> get() = _engagements.filter { it.role == MANAGER }.map { it.student }
    val members: List<Student> get() = _engagements.filter { it.role == MEMBER }.map { it.student }
    val guests: List<Student> get() = _engagements.filter { it.role == GUEST }.map { it.student }

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
