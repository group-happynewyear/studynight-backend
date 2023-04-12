package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.library.utility.Randoms
import kr.happynewyear.studynight.type.MatchSource

fun studentCreateRequestFixture(): StudentCreateRequest {
    return studentCreateRequestFixture(matchSourceFixture())
}

fun studentCreateRequestFixture(matchSource: MatchSource): StudentCreateRequest {
    val nickname = Randoms.string()
    return StudentCreateRequest(
        nickname,
        matchSource
    )
}
