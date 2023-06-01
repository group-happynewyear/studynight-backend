package kr.happynewyear.studynight.domain.model

import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.FetchType.LAZY
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.domain.model.TransactionType.*
import java.time.LocalDateTime
import java.time.LocalDateTime.MAX
import java.time.LocalDateTime.now

@Entity
@Table(
    name = "transactions"
)
class Transaction(
    student: Student,
    type: TransactionType, point: Int, expiredAt: LocalDateTime
) : Identifiable() {

    companion object {
        fun ofCharge(student: Student, point: Int, expDays: Long?): Transaction {
            val expiredAt = expDays?.let { now().plusDays(expDays) } ?: MAX
            return Transaction(student, CHARGE, point, expiredAt)
        }

        fun ofPay(student: Student, point: Int): Transaction {
            return Transaction(student, PAY, point, MAX)
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
    private val type: TransactionType = type

    @Column(
        name = "point",
        nullable = false, updatable = false, unique = false
    )
    private val point: Int = point

    @Column(
        name = "expired_at",
        nullable = false, updatable = false, unique = false
    )
    private val expiredAt: LocalDateTime = expiredAt


    @Column(
        name = "timestamp",
        nullable = false, updatable = false, unique = false
    )
    val timestamp: LocalDateTime = now()


    fun toDiff(): Int {
        return when (type) {
            CHARGE -> point
            PAY, EXPIRE -> -point
        }
    }


    fun splitIfExp(now: LocalDateTime): List<Transaction> {
        val expired = expiredAt.isBefore(now)
        return if (expired) listOf(this, expired()) else listOf(this)
    }

    private fun expired(): Transaction {
        return Transaction(student, EXPIRE, point, MAX)
    }

}
