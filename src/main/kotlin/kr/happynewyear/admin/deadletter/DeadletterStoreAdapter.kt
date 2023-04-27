package kr.happynewyear.admin.deadletter

import kr.happynewyear.library.messaging.consumer.deadletter.Deadletter
import kr.happynewyear.library.messaging.consumer.deadletter.DeadletterStore
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class DeadletterStoreAdapter(
    private val deadletterJpaRepository: DeadletterJpaRepository
) : DeadletterStore {

    override fun add(deadletter: Deadletter): String {
        val deadletterEntity = DeadletterEntity.from(deadletter)
        deadletterJpaRepository.save(deadletterEntity)
        return deadletterEntity.id
    }

    override fun pop(deadletterId: String): Deadletter? {
        val deadletterEntity = deadletterJpaRepository.findByIdOrNull(deadletterId)
        deadletterJpaRepository.deleteById(deadletterId)
        return deadletterEntity?.toDeadletter()
    }

}
