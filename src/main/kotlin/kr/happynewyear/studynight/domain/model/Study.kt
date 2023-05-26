package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.studynight.constant.EngagementRole.*
import kr.happynewyear.studynight.domain.service.EngagementRegistrationService
import kr.happynewyear.studynight.type.MatchParameter
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
@Table(
    name = "studies"
)
class Study(
    title: String, description: String, contact: StudyContact, condition: String
) : Identifiable() {

    companion object {
        fun create(
            creator: Student,
            title: String, description: String, contact: StudyContact,
            condition: MatchParameter
        ): Study {
            val study = Study(
                title, description, contact,
                JsonMarshallers.write(condition)
            )
            EngagementRegistrationService.register(study, creator, MANAGER)
            return study
        }
    }

    fun update(
        title: String, description: String, contact: StudyContact,
        condition: MatchParameter
    ) {
        this.title = title
        this.description = description
        this.contact = contact
        this.condition = JsonMarshallers.write(condition)
    }


    @Column(
        name = "title",
        nullable = false, updatable = true, unique = false
    )
    var title: String = title
        protected set

    @Column(
        name = "description",
        nullable = false, updatable = true, unique = false
    )
    var description: String = description
        protected set

    @Embedded
    var contact: StudyContact = contact
        protected set

    @Column(
        name = "condition",
        nullable = false, updatable = true, unique = false
    )
    var condition: String = condition
        protected set

    @Column(
        name = "createdAt",
        nullable = false, updatable = false, unique = false
    )
    val createdAt: LocalDateTime = now()

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
