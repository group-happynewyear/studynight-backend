package kr.happynewyear.configuration.portablemq

import com.github.josh910830.portablemq.support.template.DeadletterJsonStore
import org.springframework.stereotype.Component

@Component
class DeadletterStore : DeadletterJsonStore() {

    private val map = mutableMapOf<String, String>()


    override fun doSave(deadletterId: String, json: String) {
        map[deadletterId] = json
    }

    override fun doFindAll(): List<String> {
        return map.values.toList()
    }

    override fun doFindById(deadletterId: String): String {
        return map[deadletterId]!!
    }

    override fun clear() {
        map.clear()
    }

    override fun deleteById(deadletterId: String) {
        map.remove(deadletterId)
    }

}