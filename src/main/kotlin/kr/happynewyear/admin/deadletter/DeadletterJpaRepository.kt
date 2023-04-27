package kr.happynewyear.admin.deadletter

import org.springframework.data.jpa.repository.JpaRepository

interface DeadletterJpaRepository : JpaRepository<DeadletterEntity, String>
