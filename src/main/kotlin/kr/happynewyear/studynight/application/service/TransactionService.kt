package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.exception.InsufficientPointException
import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.service.HistoryService.Companion.availablePointOf
import kr.happynewyear.studynight.domain.service.HistoryService.Companion.historiesOn
import org.springframework.stereotype.Service
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
        if (point > availablePointOf(historiesOn(transactions, now()))) throw InsufficientPointException()
    }

}
