package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.library.marshalling.json.JsonIO
import kr.happynewyear.studynight.domain.service.InvitationRegistrationService
import kr.happynewyear.studynight.type.MatchParameter

@Entity
@Table(
    name = "matches"
)
class Match(
    study: Study,
    condition: String
) : Identifiable() {

    companion object {
        fun create(
            study: Study,
            condition: MatchParameter,
            students: Collection<Student>
        ): Match {
            val match = Match(study, JsonIO.write(condition))
            students.forEach { InvitationRegistrationService.register(match, it) }
            return match
        }
    }


    @ManyToOne(
        fetch = LAZY, optional = false,
    )
    @JoinColumn(
        name = "study_id",
        nullable = false, updatable = false, unique = false
    )
    val study: Study = study

    @Column(
        name = "condition",
        nullable = false, updatable = true, unique = false
    )
    val condition: String = condition

    @OneToMany(
        mappedBy = "match",
        cascade = [ALL], orphanRemoval = true
    )
    private val _invitations: MutableList<Invitation> = mutableListOf()
    val invitations: List<Invitation> get() = _invitations.toList()


    fun add(invitation: Invitation) {
        _invitations.add(invitation)
    }

}
