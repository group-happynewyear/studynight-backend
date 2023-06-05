package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.exception.InsufficientPointException
import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.model.Transaction
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Service
class TransactionService(
    // TODO notify

    // TODO property
    val chargePointForCreateStudy: Int = 1000,
    val chargeExpDaysForCreateStudy: Int = 14,

    val payPointForMatch: Int = 100
) {

    fun chargeForCreateStudy(student: Student) {
        student.charge(chargePointForCreateStudy, chargeExpDaysForCreateStudy)
    }


    fun payForMatch(student: Student, size: Int) {
        val point = size * payPointForMatch
        validatePoint(student, point)
        student.pay(point)
    }

    private fun validatePoint(student: Student, point: Int) = with(student) {
        val history = historyOn(transactions, now())
        val available = availablePointOf(history)
        if (point > available) throw InsufficientPointException()
    }


    private fun historyOn(transactions: List<Transaction>, timestamp: LocalDateTime): List<Transaction> {
        return transactions.map { it.splitIfExp(timestamp) }.flatten().sortedBy { it.timestamp }.toList()
    }

    private fun availablePointOf(transactions: List<Transaction>): Int {
        return transactions.map { it.toDiff() }.fold(0) { a, b -> a + b }
    }

}
