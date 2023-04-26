package kr.happynewyear.library.messaging.producer

import kr.happynewyear.library.messaging.Message
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class ProduceProcessor {

    fun <T : Message> produce(message: T, produceAction: Consumer<T>) {
        produceAction.accept(message)
    }

}
