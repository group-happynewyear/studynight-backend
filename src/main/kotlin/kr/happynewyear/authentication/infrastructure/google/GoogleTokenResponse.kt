package kr.happynewyear.authentication.infrastructure.google

data class GoogleTokenResponse(

    val access_token: String,
    val refresh_token: String?,

    val id_token: String,

    val token_type: String,
    val scope: String,
    val expires_in: Long

)
