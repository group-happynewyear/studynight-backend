package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.studynight.constant.condition.Experience.JUNIOR
import kr.happynewyear.studynight.constant.condition.Intensity.HARD
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import kr.happynewyear.studynight.constant.condition.Region.GANGNAM
import kr.happynewyear.studynight.constant.condition.Region.ONLINE
import kr.happynewyear.studynight.constant.condition.Schedule.WEEKEND_AFTERNOON

fun studentCreateRequestFixture(): StudentCreateRequest {
    val nickname = "nick"
    val schedules = setOf(WEEKEND_AFTERNOON)
    val regions = setOf(ONLINE, GANGNAM)
    val experience = JUNIOR
    val position = SERVER
    val intensity = HARD
    val scale = null
    return StudentCreateRequest(nickname, schedules, regions, experience, position, intensity, scale)
}
