package kr.happynewyear.configuration.messaging

import kr.happynewyear.library.messaging.consumer.deadletter.Deadletter
import kr.happynewyear.library.messaging.consumer.deadletter.DeadletterStore
import org.springframework.stereotype.Repository

@Repository
class DeadletterStoreAdapter(
    private val deadletterJpaRepository: DeadletterJpaRepository
) : DeadletterStore {

    override fun save(deadletter: Deadletter) {
        val deadletterEntity = DeadletterEntity.from(deadletter)
        deadletterJpaRepository.save(deadletterEntity)
    }

}