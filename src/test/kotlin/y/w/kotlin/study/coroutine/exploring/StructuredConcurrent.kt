package y.w.kotlin.study.coroutine.exploring

import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class StructuredConcurrent {
    @Test
    fun t1() = runBlocking<Unit> {
        val scope = tasks()
        println("Done")
        delay(8000)
    }

    private suspend fun tasks() = coroutineScope<Unit> {
        val job1 = launch {
            for (i in 1..20) {
                if (!isActive) {
                    println("Cancelled hello")
                    break
                }
                delay(1000)
                println("Hello $i")
            }
        }
        val job2 = launch {
            for (i in 1..20) {
                if (!isActive) {
                    println("Cancelled world")
                    break
                }
                delay(800)
                println("world $i")
            }
        }
        delay(8000)
        job1.cancel()
        job2.cancel()
    }

    @Test
    fun t2() = runBlocking {
        doWorld()
        println("Done")
    }

    // Concurrently executes both sections
    suspend fun doWorld() = coroutineScope { // this: CoroutineScope
        launch {
            delay(2000L)
            println("World 2")
        }
        launch {
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }
}
