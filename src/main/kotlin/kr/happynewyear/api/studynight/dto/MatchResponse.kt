package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.MatchResult

data class MatchResponse(
    val id: String,
    val count: Int
) {
    companion object {
        fun from(match: MatchResult): MatchResponse {
            return MatchResponse(
                match.id.toString(),
                match.count
            )
        }
    }
}
