package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.StudentResult
import kr.happynewyear.studynight.type.MatchSource
import java.time.LocalDateTime

data class StudentMyResponse(
    val id: String,
    val nickname: String,
    val condition: MatchSource,
    val point: Point
) {
    companion object {
        fun from(student: StudentResult) = with(student) {
            StudentMyResponse(id.toString(), nickname, condition, Point.from(point))
        }
    }


    data class Point(
        val available: Int,
        val expireInSeven: Int,
        val histories: List<History>
    ) {
        companion object {
            fun from(point: StudentResult.Point) = with(point) {
                Point(available, expireInSeven, histories.map { History.from(it) })
            }
        }

        data class History(
            val timestamp: LocalDateTime,
            val type: String,
            val amount: Int,
            val expiredAt: LocalDateTime
        ) {
            companion object {
                fun from(history: StudentResult.Point.History) = with(history) {
                    History(timestamp, type.name, point, expiredAt)
                }
            }
        }
    }

}
