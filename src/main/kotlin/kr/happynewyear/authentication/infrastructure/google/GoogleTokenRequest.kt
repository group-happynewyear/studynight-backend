package kr.happynewyear.authentication.infrastructure.google

data class GoogleTokenRequest(

    val code: String,

    val client_id: String,
    val client_secret: String,

    val scope: String,
    val redirect_uri: String

) {

    val grant_type = "authorization_code"

}
