package y.w.kotlin.study.coroutine.exploring

import kotlinx.coroutines.*
import kotlin.system.*
/**
 * Using GlobalScope is not a good idea. So, we achieve the same by providing async a
 * Dispatcher.
 *
 * This way, the tasks are executed by different threads. The time to finish both task1 and task2
 * is about the half of the time used by the "current" version.
 *
 * The computation is CONCURRENT - the same thread runs different tasks in interleaved way.
 * But the computation power is the same.
 *
 * - the function "main" will be blocked until runBlocking finishes.
 * - Task1 is expected to finish before task2.
 * - All tasks run in the same "main" thread.
 */
fun main() {
    runBlocking {
        logger("Main")
        val time = measureTimeMillis {
            val task1 = async(Dispatchers.Default) {
                val t = measureTimeMillis {
                    doExpensiveComputation(45)
                }
                logger("Task 1: $t ms")
            }

            val task2 = async(Dispatchers.Default) {
                val t = measureTimeMillis {
                    doExpensiveComputation(45)
                }
                logger("Task 2: $t ms")
            }

            logger("Result: ${task1.await()}, ${task2.await()}")
        }

        logger("Total Time: $time ms, is about the same as either task1 or task2 because they run in parallel.")
    }

    logger("This line runs ONLY after runBlocking finishes.")
}


