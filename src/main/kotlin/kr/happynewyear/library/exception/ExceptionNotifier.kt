package kr.happynewyear.library.exception

interface ExceptionNotifier {

    fun send(e: Exception)

}
