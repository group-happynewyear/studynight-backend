package kr.happynewyear.studynight.domain.model

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.CascadeType.REMOVE
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.constant.EngagementRole.GUEST
import kr.happynewyear.studynight.domain.service.EngagementRegistrationService
import java.util.*

@Entity
@Table(
    name = "students"
)
class Student(
    userId: UUID
) : Identifiable() {

    companion object {
        fun create(userId: UUID): Student {
            return Student(userId)
        }
    }


    @Column(
        name = "user_id",
        nullable = false, updatable = false, unique = true
    )
    private val userId: UUID = userId

    @OneToMany(
        mappedBy = "student",
        cascade = [ALL], orphanRemoval = true
    )
    private val _engagements: MutableList<Engagement> = mutableListOf()

    @OneToMany(
        mappedBy = "student",
        cascade = [REMOVE], orphanRemoval = true
    )
    private val _invitations: MutableList<Invitation> = mutableListOf()


    fun add(engagement: Engagement) {
        _engagements.add(engagement)
    }

    fun add(invitation: Invitation) {
        _invitations.add(invitation)
    }

    fun approve(invitation: Invitation) {
        EngagementRegistrationService.register(invitation.match.study, this, GUEST)
    }

}
