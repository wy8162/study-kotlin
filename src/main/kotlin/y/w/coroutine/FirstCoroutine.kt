package y.w.coroutine

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        doWorld()
        println("Done - ${Thread.currentThread().name}")
    }

}

private suspend fun doWorld() = coroutineScope {
    val job1 = launch {
        repeat(5) {
            delay(1000L)
            println("World 1 - ${Thread.currentThread().name}")
        }
    }

    val job2 = launch {
        repeat(5) {
            delay(1000L)
            println("World 2 - ${Thread.currentThread().name}")
        }
    }

    println("Finished lauch World 1 & 2")

    launch(Dispatchers.IO) {
        val value = async {
            repeat(5) {
                delay(3000L)
                println("World 3 - ${Thread.currentThread().name}")
                //throw RuntimeException("Error")
            }
            "Done by World3"
        }


        try {
            val v = withTimeout(1000L) { value.await() }
            println("Value = $v")
        } catch (e: Exception) {
            println("Caught exception: ${e.message} ${e::class}")
        }

        println("Done with this async timeout integration")
    }

    println("Finished start World3")

    println("Hello - ${Thread.currentThread().name}")

    job1.join()
    job2.join()
}
