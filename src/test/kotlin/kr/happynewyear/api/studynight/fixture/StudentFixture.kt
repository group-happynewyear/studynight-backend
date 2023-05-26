package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.api.studynight.dto.StudentUpdateRequest
import kr.happynewyear.library.utility.Randoms
import kr.happynewyear.studynight.type.MatchSource

fun studentCreateRequestFixture(): StudentCreateRequest {
    return studentCreateRequestFixture(matchSourceFixture())
}

fun studentCreateRequestFixture(matchSource: MatchSource): StudentCreateRequest {
    val nickname = Randoms.string()
    return StudentCreateRequest(nickname, matchSource)
}


fun studentUpdateRequestFixture(matchSource: MatchSource): StudentUpdateRequest {
    val nickname = Randoms.string()
    return StudentUpdateRequest(nickname, matchSource)
}
