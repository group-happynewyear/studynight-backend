package kr.happynewyear.authentication.application.client.google

data class GoogleUserinfoResponse(

    val id: String,

    val email: String,
    val verified_email: Boolean,

    val name: String,
    val family_name: String,
    val given_name: String,

    val picture: String,
    val locale: String

)
