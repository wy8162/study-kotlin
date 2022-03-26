package y.w.kotlin.study.coroutine.exploring

import kotlinx.coroutines.*
import kotlin.system.*
/**
 * The SAME main thread runs all the tasks asynchronously.
 *
 * The computation is CONCURRENT - the same thread runs different tasks in interleaved way.
 * But the computation power is the same.
 *
 * - the function "main" will be blocked until runBlocking finishes.
 * - Task1 is expected to finish before task2.
 * - All tasks run in the same "main" thread.
 *
 */
fun main() {
    runBlocking {
        logger("Main")
        val time = measureTimeMillis {
            val task1 = async {
                val t = measureTimeMillis {
                    doExpensiveComputation(45)
                }
                logger("Task 1: $t ms")
            }

            val task2 = async {
                val t = measureTimeMillis {
                    doExpensiveComputation(45)
                }
                logger("Task 2: $t ms")
            }

            logger("Result: ${task1.await()}, ${task2.await()}")
        }

        logger("Total Time: $time ms, is the sum of the time for task1 and task2")
    }

    logger("This line runs ONLY after runBlocking finishes.")
}


