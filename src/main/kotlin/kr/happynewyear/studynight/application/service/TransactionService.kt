package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.domain.model.Student
import org.springframework.stereotype.Service

@Service
class TransactionService(
    // TODO notify

    // TODO property
    val chargePointForCreateStudy: Int = 1000,
    val payPointForMatch: Int = 100
) {

    fun chargeForCreateStudy(student: Student) {
        student.charge(chargePointForCreateStudy)
    }


    fun payForMatch(student: Student, size: Int) {
        val point = size * payPointForMatch
        student.pay(point)
    }

}
