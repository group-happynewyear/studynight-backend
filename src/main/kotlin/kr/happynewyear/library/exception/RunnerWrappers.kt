package kr.happynewyear.library.exception

import java.util.function.Consumer

class RunnerWrappers {
    companion object {
        // TODO aop caught
        fun run(runnable: Runnable, onFail: Consumer<Exception>) {
            try {
                runnable.run()
            } catch (e: Exception) {
                onFail.accept(e)
            }
        }

    }
}
