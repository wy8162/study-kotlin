package y.w.coroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 * Add "-Dkotlinx.coroutines.debug" to JVM to see more details for debug.
 *
 * See book "Kotlin Programming: The Big Nerd Ranch Guide, 2nd Edition"
 *
 */
suspend fun task(taskId : Int) {
    println("Start of task${taskId} ${Thread.currentThread()}")
    yield()
    println("End of task${taskId} ${Thread.currentThread()}")
}

/**
 * The call to the launch() and runBlocking() functions resulted
 * in the coroutines executing in the same thread as the caller’s
 * coroutine scope
 */
fun main() {
    runBlocking {
        println("Before launching...${Thread.currentThread()}")
        launch { task(1) } // Runs in main
        launch { task(2) }  // Runs in main
        launch(Dispatchers.Default) { task(3) } // Runs in Default context
        println("After launching...${Thread.currentThread()}")
    }

    // Call another one
    // fetchingFlightApp()

    /**
     * Create a custom thread pool and use it to run coroutines.
     *
     * "use" function is same as Java "try-with-resources" feature.
     * "use" will close the Executor when tasks complete.
     *
     * "asCoroutineDispatcher" returns a CoroutineContext.
     *
     * Alternatively:
     *  	Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
     */
    Executors.newSingleThreadExecutor().asCoroutineDispatcher().use { context ->
        println("Start custom pool")

        runBlocking {
            launch(context) { task(11) } // Runs in custom context
            launch(context) { task(12) } // Runs in custom context
            launch { task(13) } // runs in main
        }
    }

    // Switch thread after suspension point
    // CoroutineStart.UNDISPATCHED - runs initially in current context and switch threads after the suspension point
    // CoroutineStart.DEFAULT - does not switch threads
    // CoroutineStart.LAZY - deferr execution until start() is called
    // CoroutineStart.ATOMIC - non-cancellable
    Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).asCoroutineDispatcher().use { context ->
        println("Start custom pool")

        runBlocking {
            launch(context=context, start = CoroutineStart.UNDISPATCHED) { task(21) } // Runs in main and switched thread after resume
            withContext(Dispatchers.IO) { task(22) } // Specify the context to run the coroutine
        }
    }

    // Use async
    runBlocking {
        val countOfCores : Deferred<Int> = async(Dispatchers.Default) {
            task(31);
            Runtime.getRuntime().availableProcessors()
        }

        println("Total number of COREs ${countOfCores.await()}")
    }
}
