package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.studynight.constant.ContactType.MAIL

fun studyCreateRequestFixture(): StudyCreateRequest {
    val title = "title"
    val description = "description"
    val contactType = MAIL
    val contactAddress = "email@email.com"

    return StudyCreateRequest(
        title, description,
        contactType, contactAddress,
        matchParameterFixture()
    )
}
