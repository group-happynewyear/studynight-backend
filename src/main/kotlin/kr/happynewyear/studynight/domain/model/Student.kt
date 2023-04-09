package kr.happynewyear.studynight.domain.model

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.CascadeType.REMOVE
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.constant.EngagementRole.GUEST
import kr.happynewyear.studynight.constant.condition.ConditionKey.*
import kr.happynewyear.studynight.domain.service.EngagementRegistrationService
import kr.happynewyear.studynight.type.StudentMatchCondition
import java.util.*
import kotlin.time.measureTime

@Entity
@Table(
    name = "students"
)
class Student(
    userId: UUID,
    nickname: String
) : Identifiable() {

    companion object {
        fun create(userId: UUID, nickname: String, condition: StudentMatchCondition): Student {
            val student = Student(userId, nickname)
            condition.schedules.forEach { student.add(Condition.create(student, SCHEDULE, it.name)) }
            condition.regions.forEach { student.add(Condition.create(student, REGION, it.name)) }
            student.add(Condition.create(student, EXPERIENCE, condition.experience.name))
            student.add(Condition.create(student, POSITION, condition.position.name))
            student.add(Condition.create(student, INTENSITY, condition.intensity.name))
            student.add(Condition.create(student, SCALE, condition.scale.name))
            return student
        }
    }


    @Column(
        name = "user_id",
        nullable = false, updatable = false, unique = true
    )
    private val userId: UUID = userId

    @Column(
        name = "nickname",
        nullable = false, updatable = true, unique = false
    )
    private val nickname: String = nickname

    @OneToMany(
        mappedBy = "student",
        cascade = [ALL], orphanRemoval = true
    )
    private val _conditions: MutableList<Condition> = mutableListOf()

    @OneToMany(
        mappedBy = "student",
        cascade = [ALL], orphanRemoval = true
    )
    private val _engagements: MutableList<Engagement> = mutableListOf()
    val studies: List<Study> get() = _engagements.map { it.study }


    @OneToMany(
        mappedBy = "student",
        cascade = [REMOVE], orphanRemoval = true
    )
    private val _invitations: MutableList<Invitation> = mutableListOf()


    private fun add(condition: Condition) {
        _conditions.add(condition)
    }

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
