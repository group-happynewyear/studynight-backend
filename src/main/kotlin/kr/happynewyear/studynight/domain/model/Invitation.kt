package kr.happynewyear.studynight.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable

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
    private val student: Student = student

}
