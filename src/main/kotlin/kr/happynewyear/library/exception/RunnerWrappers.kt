package kr.happynewyear.library.exception

import java.util.function.Consumer

class RunnerWrappers {
    companion object {

        fun run(runnable: Runnable, onFail: Consumer<Exception>) {
            try {
                runnable.run()
            } catch (e: Exception) {
                onFail.accept(e)
            }
        }

    }
}
