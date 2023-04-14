package kr.happynewyear.studynight.infrastructure.database

import kr.happynewyear.studynight.constant.condition.*
import kr.happynewyear.studynight.domain.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface StudentJpaRepository : JpaRepository<Student, UUID> {

    fun existsByUserId(userId: UUID): Boolean
    fun findByUserId(userId: UUID): Student?
    fun save(student: Student)

    @Query(
        "SELECT s " +
            "FROM Condition c JOIN c.student s " +
            "WHERE (" +
            "(c.key='SCHEDULE' AND c.value=:schedule) OR " +
            "(c.key='REGION' AND c.value=:region) OR " +
            "(c.key='EXPERIENCE' AND c.value IN (:experiences)) OR " +
            "(c.key='POSITION' AND c.value IN (:positions)) OR " +
            "(c.key='INTENSITY' AND c.value=:intensity) OR " +
            "(c.key='SCALE' AND c.value=:scale) " +
            ") " +
            "GROUP BY s.id " +
            "HAVING COUNT(DISTINCT c.key)=6"
    )
    fun searchByConditions(
        schedule: String,
        region: String,
        experiences: Collection<String>,
        positions: Collection<String>,
        intensity: String,
        scale: String,
    ): List<Student>

}
