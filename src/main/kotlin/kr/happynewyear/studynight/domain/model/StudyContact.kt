package kr.happynewyear.studynight.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import kr.happynewyear.studynight.constant.ContactType

@Embeddable
class StudyContact(

    @Enumerated(STRING)
    @Column(
        name = "contact_type",
        nullable = false, updatable = true, unique = false
    )
    val type: ContactType,

    @Column(
        name = "contact_address",
        nullable = false, updatable = true, unique = false
    )
    val address: String

)
