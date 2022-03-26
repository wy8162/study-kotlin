package y.w.kotlin.study.coroutine.exploring

import kotlinx.coroutines.*
import mu.*
import y.w.kotlin.study.coroutine.exploring.util.*

val log = KotlinLogging.logger {}

fun logger(name: String): Unit {
    log.info("$name -> ${Thread.currentThread().name}")
}

suspend fun doDelayTask2(): String {
    delay(1500)
    logger("Task2")
    return "Task2 done"
}

suspend fun doDelayTask1(): String {
    delay(1000)
    logger("Task1")
    return "Task1 done"
}

suspend fun doExpensiveComputation(num: Int): Int {
    return fib(num)
}