package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.domain.service.InvitationRegistrationService

@Entity
@Table(
    name = "matches"
)
class Match(
    study: Study,
) : Identifiable() {

    companion object {
        fun create(study: Study, students: Collection<Student>): Match {
            val match = Match(study)
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
