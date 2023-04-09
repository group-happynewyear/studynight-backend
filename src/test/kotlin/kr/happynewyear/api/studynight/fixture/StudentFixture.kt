package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.studynight.constant.condition.Experience.JUNIOR
import kr.happynewyear.studynight.constant.condition.Intensity.HARD
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import kr.happynewyear.studynight.constant.condition.Region.GANGNAM
import kr.happynewyear.studynight.constant.condition.Region.ONLINE
import kr.happynewyear.studynight.constant.condition.Scale.M
import kr.happynewyear.studynight.constant.condition.Schedule.WEEKEND_AFTERNOON
import kr.happynewyear.studynight.type.StudentMatchCondition

fun studentCreateRequestFixture(): StudentCreateRequest {
    val nickname = "nick"
    return StudentCreateRequest(
        nickname,
        studentMatchConditionFixture()
    )
}

fun studentMatchConditionFixture(): StudentMatchCondition {
    val schedules = setOf(WEEKEND_AFTERNOON)
    val regions = setOf(ONLINE, GANGNAM)
    val experience = JUNIOR
    val position = SERVER
    val intensity = HARD
    val scale = M
    return StudentMatchCondition(schedules, regions, experience, position, intensity, scale)
}