package kr.happynewyear.bff.web.dto.dictionary

import kr.happynewyear.studynight.constant.condition.*

val dictionary: Map<String, String> = mapOf(
    Pair(ConditionKey.SCHEDULE.name, "일정"),
    Pair(ConditionKey.REGION.name, "지역"),
    Pair(ConditionKey.EXPERIENCE.name, "경력"),
    Pair(ConditionKey.POSITION.name, "직군"),
    Pair(ConditionKey.INTENSITY.name, "성향"),
    Pair(ConditionKey.SCALE.name, "규모"),

    Pair(Schedule.WEEKDAY_MORNING.name, "평일오전"),
    Pair(Schedule.WEEKDAY_AFTERNOON.name, "평일오후"),
    Pair(Schedule.WEEKEND_MORNING.name, "주말오전"),
    Pair(Schedule.WEEKEND_AFTERNOON.name, "주말오후"),

    Pair(Region.ONLINE.name, "온라인"),
    Pair(Region.GANGNAM.name, "강남"),
    Pair(Region.JONGNO.name, "종로"),
    Pair(Region.SINCHON.name, "신촌"),
    Pair(Region.SEONGSU.name, "성수"),
    Pair(Region.BUNDANG.name, "분당"),

    Pair(Experience.JUNIOR.name, "주니어"),
    Pair(Experience.MIDDLE.name, "중니어"),
    Pair(Experience.SENIOR.name, "시니어"),

    Pair(Position.WEB.name, "웹"),
    Pair(Position.MOBILE.name, "모바일"),
    Pair(Position.SERVER.name, "서버"),
    Pair(Position.CLOUD.name, "클라우드"),
    Pair(Position.DATA.name, "데이터"),
    Pair(Position.ETC.name, "기타"),

    Pair(Intensity.EASY.name, "순한맛"),
    Pair(Intensity.NORMAL.name, "중간맛"),
    Pair(Intensity.HARD.name, "매운맛"),

    Pair(Scale.S.name, "2+"),
    Pair(Scale.M.name, "4+"),
    Pair(Scale.L.name, "6+"),
    Pair(Scale.XL.name, "8+")
)
