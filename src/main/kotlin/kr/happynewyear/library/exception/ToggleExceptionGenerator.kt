package kr.happynewyear.library.exception

class ToggleExceptionGenerator(
    on: Boolean
) {

    private var switch = on

    fun push() {
        val temp = switch;
        switch = !switch
        if (temp) throw RuntimeException("generated")
    }

}
