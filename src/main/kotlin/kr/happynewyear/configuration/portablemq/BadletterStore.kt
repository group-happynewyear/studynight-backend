package kr.happynewyear.configuration.portablemq

import com.github.josh910830.portablemq.kafka.consumer.badletter.Badletter
import com.github.josh910830.portablemq.kafka.consumer.badletter.interfaces.BadletterStore
import org.springframework.stereotype.Component

@Component
class BadletterStore : BadletterStore {

    private val map = mutableMapOf<String, Badletter>()


    override fun save(badletter: Badletter) {
        map[badletter.id] = badletter
    }

    override fun findAll(): List<Badletter> {
        return map.values.toList()
    }

    override fun findById(badletterId: String): Badletter {
        return map[badletterId]!!
    }

    override fun clear() {
        map.clear()
    }

    override fun deleteById(badletterId: String) {
        map.remove(badletterId)
    }

}