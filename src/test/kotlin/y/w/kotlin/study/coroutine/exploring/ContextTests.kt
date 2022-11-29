package y.w.kotlin.study.coroutine.exploring

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext

class ContextTests {
    val scope = CoroutineScope(SupervisorJob())

    @Test
    fun t1() {
        val ctx1 = EmptyCoroutineContext
        val ctx2 = ctx1 + CoroutineName("somename")
        val ctx3 = ctx2 + CoroutineName("somename2")
        assertThat("somename").isEqualTo(ctx2[CoroutineName]!!.name)
    }

    @Test
    fun t2(): Unit = runBlocking(CoroutineName("top")) {
        println("Top coroutine - context: $coroutineContext")
        launch(coroutineContext + CoroutineName("2nd")) {
            println("2nd coroutine - context: $coroutineContext")
            async(coroutineContext + CoroutineName("3rd")) {
                println("3rd async coroutine - context: $coroutineContext ")
                withContext(coroutineContext + CoroutineName("newContextInAsync")) {
                    println("4th coroutine - context: $coroutineContext")
                }
            }
            doTask()
        }
    }

    private suspend fun doTask() {
        scope.launch(coroutineContext + CoroutineName("task")) {
            println("Task coroutine - context: $coroutineContext")
        }
    }

    @Test
    fun testSwitchContext() {
        newSingleThreadContext("Ctx1").use { ctx1 ->
            newSingleThreadContext("Ctx2").use { ctx2 ->
                runBlocking(ctx1) {
                    println("Started in ctx1 $coroutineContext")
                    withContext(ctx2) {
                        println("Working in ctx2 $coroutineContext")
                    }
                    println("Back to ctx1 $coroutineContext")
                }
            }
        }
    }
}
