package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.constant.EngagementRole.GUEST
import kr.happynewyear.studynight.domain.model.InvitationState.*
import kr.happynewyear.studynight.domain.service.EngagementRegistrationService

@Entity
@Table(
    name = "invitations"
)
class Invitation(
    match: Match,
    student: Student
) : Identifiable() {

    companion object {
        fun create(match: Match, student: Student): Invitation {
            return Invitation(match, student)
        }
    }


    @ManyToOne(
        fetch = LAZY, optional = false,
    )
    @JoinColumn(
        name = "match_id",
        nullable = false, updatable = false, unique = false
    )
    val match: Match = match

    @ManyToOne(
        fetch = LAZY, optional = false,
    )
    @JoinColumn(
        name = "student_id",
        nullable = false, updatable = false, unique = false
    )
    val student: Student = student

    @Enumerated(STRING)
    @Column(
        name = "state",
        nullable = false, updatable = true, unique = false
    )
    private var state: InvitationState = PENDING // TODO batch delete pending

    fun confirm(accept: Boolean) {
        state = if (accept) ACCEPT else REJECT
        if (accept) EngagementRegistrationService.register(match.study, student, GUEST)
    }

}
