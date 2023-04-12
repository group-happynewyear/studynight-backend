package kr.happynewyear.api.studynight.fixture

import kr.happynewyear.studynight.constant.condition.Experience.JUNIOR
import kr.happynewyear.studynight.constant.condition.Intensity.HARD
import kr.happynewyear.studynight.constant.condition.Position.SERVER
import kr.happynewyear.studynight.constant.condition.Region.GANGNAM
import kr.happynewyear.studynight.constant.condition.Scale.M
import kr.happynewyear.studynight.constant.condition.Schedule.WEEKEND_AFTERNOON
import kr.happynewyear.studynight.type.MatchParameter

fun matchParameterFixture(): MatchParameter {
    val schedule = WEEKEND_AFTERNOON
    val region = GANGNAM
    val experiences = setOf(JUNIOR)
    val positions = setOf(SERVER)
    val intensity = HARD
    val scale = M
    return MatchParameter(schedule, region, experiences, positions, intensity, scale)
}
