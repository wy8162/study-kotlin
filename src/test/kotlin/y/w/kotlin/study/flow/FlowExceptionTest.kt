package y.w.kotlin.study.flow

import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class FlowExceptionTest {
    private fun simple(limit: Int = 0): Flow<Int> = flow {
        for (i in 1..4) {
            emit(i)
        }
    }
        .map { v ->
            check(v >= limit) { "invalid value $v" }
            v
        }

    @Test
    fun `exception in emitter or collector stops the flow`(): Unit = runBlocking {
        assertThrows<IllegalStateException>() {
            simple().collect { v ->
                check(v <= 2) { "Collected value $v" }
                println("#1 collected $v")
            }
        }
    }

    @Test
    fun `catch exception from upstream operators`(): Unit = runBlocking {
        val f: Flow<Int> = flow {
            for (i in 1..4) {
                emit(i)
            }
        }

        // Exceptions thrown in collector escapes
        assertThrows<IllegalStateException> {
            f
                .catch { e -> println("Caught exception $e") }
                .collect {
                    if (it % 2 == 0) throw IllegalStateException("Caught value $it")
                    println(it)
                }
        }

        // Throws above catch operator won't escape
        assertDoesNotThrow {
            f
                .onEach {
                    // Moved collector block here
                    if (it % 3 == 0) throw IllegalStateException("Caught value $it")
                    println(it)
                }
                .catch { e -> println("Caught exception $e") }
                .collect()
        }

        // Rethrow the exception if needed, giving a chance
        // to analyze and translate exceptions.
        assertThrows<RuntimeException> {
            f
                .onEach {
                    // Moved collector block here
                    if (it % 3 == 0) throw IllegalStateException("Caught value $it")
                    println(it)
                }
                .catch { e ->
                    println("Caught exception $e")
                    throw RuntimeException()
                }
                .collect()
        }
    }

    @Test
    fun `make busy flow cancellable`(): Unit = runBlocking {
        launch {
            try {
                (1..5).asFlow().cancellable().collect { value ->
                    if (value == 3) cancel()
                    println(value)
                }
            } catch (e: Exception) {
                println("Caught exception - JobCancellationException: $e")
            }
        }
    }
}
