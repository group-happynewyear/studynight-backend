package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.constant.condition.*

fun studyCreateRequestFixture(): StudyCreateRequest {
    val title = "title"
    val description = "description"
    val contactType = ContactType.MAIL
    val contactAddress = "email@email.com"
    val schedule = Schedule.WEEKEND_AFTERNOON
    val region = Region.GANGNAM
    val experiences = setOf(Experience.JUNIOR)
//    val experiences = emptySet<Experience>()
    val positions = setOf(Position.SERVER)
    val intensity = Intensity.HARD
    val scale = Scale.M

    return StudyCreateRequest(
        title, description,
        contactType, contactAddress,
        schedule, region, experiences, positions, intensity, scale
    )
}
