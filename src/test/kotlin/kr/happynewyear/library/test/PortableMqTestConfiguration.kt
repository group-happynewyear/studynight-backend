package kr.happynewyear.library.test

import com.github.josh910830.portablemq.spring.event.SpringListenerSwitch
import com.github.josh910830.portablemq.support.test.TestSpringListenerSwitch
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class PortableMqTestConfiguration {

    @Bean
    fun springListenerSwitch(): SpringListenerSwitch {
        return TestSpringListenerSwitch()
    }

}