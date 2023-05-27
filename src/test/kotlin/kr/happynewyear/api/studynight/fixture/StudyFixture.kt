package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.api.studynight.dto.StudyUpdateRequest
import kr.happynewyear.library.utility.Randoms
import kr.happynewyear.studynight.constant.ContactType.KAKAOTALK_OPENCHAT
import kr.happynewyear.studynight.type.MatchParameter

fun studyCreateRequestFixture(): StudyCreateRequest {
    return studyCreateRequestFixture(matchParameterFixture())
}

fun studyCreateRequestFixture(matchParameter: MatchParameter): StudyCreateRequest {
    val title = Randoms.string()
    val description = "description"
    val contactType = KAKAOTALK_OPENCHAT
    val contactAddress = "https://kakao.link"
    return StudyCreateRequest(title, description, contactType, contactAddress, matchParameter)
}


fun studyUpdateRequestFixture(matchParameter: MatchParameter): StudyUpdateRequest {
    val title = Randoms.string()
    val description = "description"
    val contactType = KAKAOTALK_OPENCHAT
    val contactAddress = "https://kakao.link"
    return StudyUpdateRequest(title, description, contactType, contactAddress, matchParameter)
}
