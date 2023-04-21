package kr.happynewyear.configuration.schedule

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync

@Configuration
@EnableAsync(proxyTargetClass = true)
class AsyncConfiguration
