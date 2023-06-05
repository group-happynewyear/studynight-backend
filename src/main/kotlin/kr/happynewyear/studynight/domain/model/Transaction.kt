package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.domain.model.TransactionType.*
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
@Table(
    name = "transactions"
)
class Transaction(
    student: Student,
    type: TransactionType, point: Int
) : Identifiable() {

    companion object {
        fun ofCharge(student: Student, point: Int): Transaction {
            return Transaction(student, CHARGE, point)
        }

        fun ofPay(student: Student, point: Int): Transaction {
            return Transaction(student, PAY, point)
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
        name = "type",
        nullable = false, updatable = false, unique = false
    )
    val type: TransactionType = type

    @Column(
        name = "point",
        nullable = false, updatable = false, unique = false
    )
    val point: Int = point


    @Column(
        name = "timestamp",
        nullable = false, updatable = false, unique = false
    )
    val timestamp: LocalDateTime = now()


    fun toDiff(): Int {
        return when (type) {
            CHARGE -> point
            PAY -> -point
        }
    }

}
