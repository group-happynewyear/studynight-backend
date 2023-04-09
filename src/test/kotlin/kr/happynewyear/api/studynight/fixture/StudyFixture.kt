package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.studynight.constant.ContactType.MAIL
import kr.happynewyear.studynight.constant.condition.Experience.JUNIOR
import kr.happynewyear.studynight.constant.condition.Intensity.HARD
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import kr.happynewyear.studynight.constant.condition.Region.GANGNAM
import kr.happynewyear.studynight.constant.condition.Scale.M
import kr.happynewyear.studynight.constant.condition.Schedule.WEEKEND_AFTERNOON
import kr.happynewyear.studynight.type.StudyMatchCondition

fun studyCreateRequestFixture(): StudyCreateRequest {
    val title = "title"
    val description = "description"
    val contactType = MAIL
    val contactAddress = "email@email.com"

    return StudyCreateRequest(
        title, description,
        contactType, contactAddress,
        studyMatchConditionFixture()
    )
}

fun studyMatchConditionFixture(): StudyMatchCondition {
    val schedule = WEEKEND_AFTERNOON
    val region = GANGNAM
    val experiences = setOf(JUNIOR)
    val positions = setOf(SERVER)
    val intensity = HARD
    val scale = M
    return StudyMatchCondition(schedule, region, experiences, positions, intensity, scale)
}
