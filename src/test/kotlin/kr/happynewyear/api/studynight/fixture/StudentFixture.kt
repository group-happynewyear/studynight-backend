package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudentCreateRequest

fun studentCreateRequestFixture(): StudentCreateRequest {
    val nickname = "nick"
    return StudentCreateRequest(
        nickname,
        matchSourceFixture()
    )
}
