package kr.happynewyear.configuration.portablemq

import com.github.josh910830.portablemq.core.consumer.deadletter.interfaces.RedriveTokenManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RedriveTokenManager(
    @Value("\${deadletter.redrive-token}") private val token: String,
) : RedriveTokenManager {

    override fun issue(deadletterId: String): String {
        return token
    }

    override fun authenticate(deadletterId: String, redriveToken: String): Boolean {
        return redriveToken == token
    }

}