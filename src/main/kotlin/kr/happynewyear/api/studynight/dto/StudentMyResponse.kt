package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.StudentResult
import kr.happynewyear.studynight.domain.model.TransactionType
import kr.happynewyear.studynight.type.MatchSource
import java.time.LocalDateTime

data class StudentMyResponse(
    val id: String,
    val nickname: String,
    val condition: MatchSource,
    val point: Int,
    val transactions: List<Transaction>
) {
    companion object {
        fun from(student: StudentResult) = with(student) {
            StudentMyResponse(
                id.toString(), nickname, condition, point,
                transactions.map { Transaction(it.timestamp, it.type, it.point) }
            )
        }
    }

    data class Transaction(
        val timestamp: LocalDateTime,
        val type: TransactionType,
        val point: Int
    )
}
