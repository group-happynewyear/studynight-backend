package kr.happynewyear.studynight.domain.service

import kr.happynewyear.studynight.domain.model.Transaction
import kr.happynewyear.studynight.domain.model.TransactionType.CHARGE
import java.time.LocalDateTime

class HistoryService {
    companion object {

        fun historiesOn(transactions: List<Transaction>, standard: LocalDateTime): List<Transaction> {
            return transactions.map { it.splitIfExp(standard) }.flatten().sortedBy { it.timestamp }.toList()
        }

        fun availablePointOf(histories: List<Transaction>): Int {
            return histories.map { it.toDiff() }.fold(0) { a, b -> a + b }
        }

        fun expirePointOf(histories: List<Transaction>, standard: LocalDateTime, threshold: LocalDateTime): Int {
            return histories
                .filter { it.type == CHARGE && it.expiredAt.isAfter(standard) && it.expiredAt.isBefore(threshold) }
                .map { it.point }.fold(0) { a, b -> a + b }
        }

    }
}