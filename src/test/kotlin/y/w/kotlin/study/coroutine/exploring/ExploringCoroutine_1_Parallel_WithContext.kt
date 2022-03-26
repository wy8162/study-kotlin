package y.w.kotlin.study.coroutine.exploring

import kotlinx.coroutines.*
import kotlin.system.*
/**
 * Using withContext to achieve same effect of parallelism. withContext applies thread from new context.
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
            val task1 = async {
                val t = measureTimeMillis {
                    suspendableDoExpensiveComputation(45)
                }
                logger("Task 1: $t ms")
            }

            val task2 = async {
                val t = measureTimeMillis {
                    suspendableDoExpensiveComputation(45)
                }
                logger("Task 2: $t ms")
            }

            logger("Result: ${task1.await()}, ${task2.await()}")
        }

        logger("Total Time: $time ms, is about the same as either task1 or task2 because they run in parallel.")
    }

    logger("This line runs ONLY after runBlocking finishes.")
}

private suspend fun suspendableDoExpensiveComputation(num: Int): Int {
    return withContext(Dispatchers.Default) {
        logger("Suspendable task: runs in different thread")
        doExpensiveComputation(num)
    }.also {
        logger("Suspendable task $it: runs in main thread")
    }
}


