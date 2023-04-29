package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.constant.EngagementRole

@Entity
@Table(
    name = "engagements"
)
class Engagement(
    study: Study,
    student: Student,
    role: EngagementRole
) : Identifiable() {

    companion object {
        fun create(study: Study, student: Student, role: EngagementRole): Engagement {
            return Engagement(study, student, role)
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
        name = "role",
        nullable = false, updatable = true, unique = false
    )
    val role: EngagementRole = role

}
