package kr.happynewyear.api.authentication.dto

import jakarta.validation.constraints.Pattern

data class RefreshRequest(
    @field:Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
    val refreshToken: String
)
