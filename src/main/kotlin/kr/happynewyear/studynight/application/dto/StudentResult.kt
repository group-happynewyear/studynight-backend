package kr.happynewyear.studynight.application.dto

import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.model.Transaction
import kr.happynewyear.studynight.domain.model.TransactionType
import kr.happynewyear.studynight.domain.service.HistoryService.Companion.availablePointOf
import kr.happynewyear.studynight.domain.service.HistoryService.Companion.expirePointOf
import kr.happynewyear.studynight.domain.service.HistoryService.Companion.historiesOn
import kr.happynewyear.studynight.type.MatchSource
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*

data class StudentResult(
    val id: UUID,
    val nickname: String,
    val condition: MatchSource,
    val point: Point
) {
    companion object {
        fun from(student: Student): StudentResult = with(student) {
            return StudentResult(id, nickname, condition, Point.from(transactions))
        }
    }

    data class Point(
        val available: Int,
        val expireInSeven: Int,
        val histories: List<History>
    ) {
        companion object {
            fun from(transactions: List<Transaction>): Point {
                val standard = now()
                val histories = historiesOn(transactions, standard)
                return Point(
                    availablePointOf(histories),
                    expirePointOf(histories, standard, standard.plusDays(7)),
                    histories.map { History.from(it) })
            }
        }

        data class History(
            val timestamp: LocalDateTime,
            val type: TransactionType,
            val point: Int,
            val expiredAt: LocalDateTime
        ) {
            companion object {
                fun from(transaction: Transaction): History = with(transaction) {
                    return History(timestamp, type, point, expiredAt)
                }
            }
        }
    }

}
