package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.constant.condition.ConditionKey
import java.util.*

@Entity
@Table(
    name = "conditions"
)
class Condition(
    student: Student,
    key: ConditionKey, value: String
) : Identifiable() {

    companion object {
        fun create(
            student: Student,
            key: ConditionKey, value: String
        ): Condition {
            return Condition(student, key, value)
        }
    }


    @ManyToOne(
        fetch = LAZY, optional = false,
    )
    @JoinColumn(
        name = "student_id",
        nullable = false, updatable = false, unique = false
    )
    private val student: Student = student

    @Enumerated(STRING)
    @Column(
        name = "k",
        nullable = false, updatable = false, unique = false
    )
    val key: ConditionKey = key

    @Column(
        name = "v",
        nullable = false, updatable = false, unique = false
    )
    val value: String = value

}
