package kr.happynewyear.studynight.application.dto

import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.model.Transaction
import kr.happynewyear.studynight.domain.model.TransactionType
import kr.happynewyear.studynight.type.MatchSource
import java.time.LocalDateTime
import java.util.*

data class StudentResult(
    val id: UUID,
    val nickname: String,
    val condition: MatchSource,
    val point: Int,
    val transactions: List<Transaction>
) {
    companion object {
        fun from(student: Student): StudentResult = with(student) {
            return StudentResult(
                id, nickname, condition, point,
                transactions.map { Transaction(it.timestamp, it.type, it.point) })
        }
    }

    data class Transaction(
        val timestamp: LocalDateTime,
        val type: TransactionType,
        val point: Int
    )

}
