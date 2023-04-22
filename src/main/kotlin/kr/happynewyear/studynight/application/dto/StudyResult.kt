package kr.happynewyear.studynight.application.dto

import kr.happynewyear.library.marshalling.json.JsonMarshallers
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.domain.model.Study
import kr.happynewyear.studynight.type.MatchParameter
import java.time.LocalDateTime
import java.util.*

data class StudyResult(
    val id: UUID,

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
        fun from(study: Study): StudyResult {
            return StudyResult(
                study.id,
                study.title, study.description,
                study.contactType, study.contactAddress,
                JsonMarshallers.read(study.condition, MatchParameter::class.java),
                study.managers.map { Student(it.id) },
                study.members.map { Student(it.id) },
                study.guests.map { Student(it.id) },
                study.createdAt
            )
        }
    }

    data class Student(
        val id: UUID
    )

}
