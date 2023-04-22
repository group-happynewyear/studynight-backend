package kr.happynewyear.api.studynight.dto

import kr.happynewyear.studynight.application.dto.StudyResult
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.type.MatchParameter
import java.time.LocalDateTime

data class StudyResponse(
    val id: String,

    val title: String,
    val description: String,

    val contactType: ContactType,
    val contactAddress: String,

    val condition: MatchParameter,

    val managers: List<Student>,
    val members: List<Student>,
    val guests: List<Student>,

    val createdAt: LocalDateTime
) {
    companion object {
        fun from(study: StudyResult): StudyResponse {
            return StudyResponse(
                study.id.toString(),
                study.title, study.description,
                study.contactType, study.contactAddress,
                study.condition,
                study.managers.map { Student(it.id.toString()) },
                study.members.map { Student(it.id.toString()) },
                study.guests.map { Student(it.id.toString()) },
                study.createdAt
            )
        }
    }

    data class Student(
        val id: String
    )

}
