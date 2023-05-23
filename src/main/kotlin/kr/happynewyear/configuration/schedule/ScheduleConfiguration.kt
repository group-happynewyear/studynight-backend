package kr.happynewyear.configuration.schedule

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
class ScheduleConfiguration
