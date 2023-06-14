package kr.happynewyear.studynight.domain.model

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.CascadeType.REMOVE
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import kr.happynewyear.library.entity.Identifiable
import kr.happynewyear.studynight.application.exception.InsufficientPointException
import kr.happynewyear.studynight.constant.condition.*
import kr.happynewyear.studynight.constant.condition.ConditionKey.*
import kr.happynewyear.studynight.type.MatchSource
import java.util.*
import java.util.function.Function.identity
import java.util.stream.Collectors.toMap

@Entity
@Table(
    name = "students"
)
class Student(
    userId: UUID,
    nickname: String
) : Identifiable() {

    companion object {
        fun create(userId: UUID, nickname: String, condition: MatchSource): Student {
            val student = Student(userId, nickname)
            student.register(condition)
            return student
        }
    }

    fun update(nickname: String, condition: MatchSource) {
        this.nickname = nickname
        update(condition)
    }


    @Column(
        name = "user_id",
        nullable = false, updatable = false, unique = true
    )
    val userId: UUID = userId

    @Column(
        name = "nickname",
        nullable = false, updatable = true, unique = false
    )
    var nickname: String = nickname
        protected set

    @OneToMany(
        mappedBy = "student",
        cascade = [ALL], orphanRemoval = true
    )
    private val _conditions: MutableList<Condition> = mutableListOf()
    val condition: MatchSource get() = compact()

    @OneToMany(
        mappedBy = "student",
        cascade = [ALL], orphanRemoval = true
    )
    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions get() = _transactions.toList()
    val point get() = transactions.map { it.toDiff() }.fold(0) { a, b -> a + b }

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
    val invitations get() = _invitations.toList()


    private fun register(condition: MatchSource) {
        condition.flatten(this).forEach { _conditions.add(it) }
    }

    private fun update(condition: MatchSource) {
        val old = _conditions.toList()
        val new = condition.flatten(this)

        val del = old.toMutableList()
        val ins = mutableListOf<Condition>()

        val hashFunc: (c: Condition) -> String = { "${it.key}-${it.value}" }
        val oldTable = old.stream().collect(toMap(hashFunc, identity()))
        new.forEach {
            val hash = hashFunc.invoke(it)
            val nCon = oldTable[hash]
            if (nCon != null) del.remove(nCon)
            else ins.add(it)
        }

        _conditions.removeAll(del)
        _conditions.addAll(ins)
    }


    fun add(engagement: Engagement) {
        _engagements.add(engagement)
    }

    fun add(invitation: Invitation) {
        _invitations.add(invitation)
    }


    private fun MatchSource.flatten(s: Student): List<Condition> {
        val l = mutableListOf<Condition>()
        l.addAll(schedules.map { Condition.create(s, SCHEDULE, it.name) })
        l.addAll(regions.map { Condition.create(s, REGION, it.name) })
        l.add(Condition.create(s, EXPERIENCE, experience.name))
        l.add(Condition.create(s, POSITION, position.name))
        l.add(Condition.create(s, INTENSITY, intensity.name))
        l.add(Condition.create(s, SCALE, scale.name))
        return l
    }

    private fun compact(): MatchSource {
        val schedules = mutableSetOf<Schedule>()
        val regions = mutableSetOf<Region>()
        lateinit var experience: Experience
        lateinit var position: Position
        lateinit var intensity: Intensity
        lateinit var scale: Scale
        _conditions.forEach {
            with(it) {
                when (key) {
                    SCHEDULE -> schedules.add(Schedule.valueOf(value))
                    REGION -> regions.add(Region.valueOf(value))
                    EXPERIENCE -> experience = Experience.valueOf(value)
                    POSITION -> position = Position.valueOf(value)
                    INTENSITY -> intensity = Intensity.valueOf(value)
                    SCALE -> scale = Scale.valueOf(value)
                }
            }
        }
        return MatchSource(schedules, regions, experience, position, intensity, scale)
    }


    fun isInvitable(study: Study): Boolean {
        if (studies.toSet().contains(study)) return false
        if (invitations.map { it.match.study }.toSet().contains(study)) return false
        return true
    }


    fun charge(point: Int) {
        val tx = Transaction.ofCharge(this, point)
        _transactions.add(tx)
    }

    fun pay(point: Int) {
        if (point > this.point) throw InsufficientPointException()
        val tx = Transaction.ofPay(this, point)
        _transactions.add(tx)
    }

}
