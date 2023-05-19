package kr.happynewyear.configuration.portablemq

import com.github.josh910830.portablemq.kafka.consumer.badletter.Badletter
import com.github.josh910830.portablemq.kafka.consumer.badletter.interfaces.BadletterNotifier
import kr.happynewyear.library.exception.ExceptionNotifier
import org.springframework.stereotype.Component

@Component
class BadletterNotifier(
    private val exceptionNotifier: ExceptionNotifier
) : BadletterNotifier {

    override fun notify(badletter: Badletter, exception: Exception) {
        exceptionNotifier.send(RuntimeException(badletter.toString(), exception))
    }

}