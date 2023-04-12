package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.studynight.constant.condition.Experience.JUNIOR
import kr.happynewyear.studynight.constant.condition.Intensity.NORMAL
import kr.happynewyear.studynight.constant.condition.Position
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import kr.happynewyear.studynight.constant.condition.Region.GANGNAM
import kr.happynewyear.studynight.constant.condition.Scale.M
import kr.happynewyear.studynight.constant.condition.Schedule.WEEKEND_AFTERNOON
import kr.happynewyear.studynight.type.MatchSource

fun matchSourceFixture(): MatchSource {
    return matchSourceFixture(
        SERVER
    )
}

fun matchSourceFixture(
    position: Position
): MatchSource {
    val schedules = setOf(WEEKEND_AFTERNOON)
    val regions = setOf(GANGNAM)
    val experience = JUNIOR
    val intensity = NORMAL
    val scale = M
    return MatchSource(schedules, regions, experience, position, intensity, scale)
}
