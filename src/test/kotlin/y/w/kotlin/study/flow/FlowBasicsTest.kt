package y.w.kotlin.study.flow

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class FlowBasicsTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun a(): Unit = runBlocking {
        val f1: Flow<Int> = flow {
            for (i in 1..3) {
                emit(i * 2)
            }
        }

        f1.collect { value -> println(value) }

        val f2: Flow<Int> = flow {
            for (i in 1..5) {
                delay(100)
                emit(i * 2)
            }
        }

        // Cancelling flow
        withTimeoutOrNull(250) {
            f2.collect { value -> println(value) }
        }
    }

    @Test
    fun b(): Unit = runBlocking {
        val f1 = flowOf(100)
        f1.collect { v -> println(v) }

        assertThat(flowOf(1).single()).isEqualTo(1)
        assertThat(
            flowOf(2, 1).takeWhile {
                it > 1
            }.single()
        ).isEqualTo(2)
    }

    @Test
    fun c(): Unit = runBlocking {
        (1..3).asFlow()
            .transform { v ->
                emit(v)
                emit(v + 1)
            }
            .collect { v -> println(v) }
    }

    // Flow runs in the context of the calling coroutine.
    @Test
    fun d(): Unit = runBlocking {
        logger.info("Outer coroutine: $coroutineContext, thread=${Thread.currentThread().name}")
        (1..3).asFlow()
            .collect {
                logger.info("Inner $it coroutine: $coroutineContext, thread=${Thread.currentThread().name}")
            }
    }

    // Flow runs in the specified context
    @Test
    fun e(): Unit = runBlocking {
        logger.info("Outer coroutine")
        withContext(coroutineContext + CoroutineName("2nd")) {
            (1..3).asFlow()
                .collect {
                    logger.info("Inner $it coroutine")
                }
        }
    }

    @Test
    fun f(): Unit = runBlocking {
        simple().collect { value ->
            // runs in main thread.
            logger.info("Collected value $value")
        }
    }

    private fun simple(): Flow<Int> = flow {
        // runs in background thread.
        for (i in 1..100) {
            delay(100) // pretend we are computing it in CPU-consuming way
            logger.info("Emitting $i")
            emit(i) // emit next value
        }
    }.flowOn(Dispatchers.Default) // RIGHT way to change context for CPU-consuming code in flow builder

    private fun simple2(): Flow<Int> = flow {
        // runs in background thread.
        for (i in 1..100) {
            delay(100) // pretend we are computing it in CPU-consuming way
            logger.info("Emitting $i")
            emit(i) // emit next value
        }
    }

    /**
     *  flowOn operator uses the same buffering mechanism when it has to change a CoroutineDispatcher
     */
    @Test
    fun g_buffering_with_flowOn(): Unit = runBlocking {
        // No buffer, runs sequentially
        val time1 = measureTimeMillis {
            simple()
                .collect { value ->
                    delay(300)
                    println(value)
                }
        }

        // Buffer makes collect and flow run concurrently
        val time2 = measureTimeMillis {
            simple()
                .buffer()
                .collect { value ->
                    delay(300)
                    println(value)
                }
        }

        assertTrue(time1 > time2)
        println("These two are about the same because flowOn has buffer builtin - time 1 = $time1, time 2 = $time2")
    }

    /**
     * Using buffer to explicitly make it run concurrently: collection vs flow run concurrently.
     */
    @Test
    fun g_buffer(): Unit = runBlocking {
        // No buffer, runs sequentially
        val time3 = measureTimeMillis {
            simple2()
                .collect { value ->
                    delay(300)
                    println(value)
                }
        }

        // Buffer makes collect and flow run concurrently
        val time4 = measureTimeMillis {
            simple2()
                .buffer()
                .collect { value ->
                    delay(300)
                    println(value)
                }
        }

        assertTrue(time3 > time4)
        println("This significant difference with buffer being used - time 1 = $time3, time 2 = $time4")
    }
}
