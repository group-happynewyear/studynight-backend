package kr.happynewyear.library.security

import org.springframework.security.core.annotation.AuthenticationPrincipal


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
@AuthenticationPrincipal(expression = "#this")
annotation class Authenticated