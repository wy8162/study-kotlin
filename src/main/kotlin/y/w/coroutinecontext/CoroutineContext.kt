package y.w.coroutinecontext

import kotlinx.coroutines.*

/**
 * https://medium.com/@Mayank_Mehta/coroutines-context-cancellation-supervisorjob-scenarios-part-1-95537debab69
 *
 * To create an instance of CoroutineScope you need to provide CoroutineContext. To create an instance of
 * CoroutineContext you can provide it set of Elements which are Dispatchers, CoroutineName, Job and
 * CoroutineExceptionHandler.
 */
fun main() = runBlocking {

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(coroutineContext)
        println(throwable.message)
    }
    val parentScope = CoroutineScope(CoroutineName("Sample CN") + Job() + Dispatchers.IO +
            coroutineExceptionHandler)

    println("parentScope: CoroutineName ${parentScope.coroutineContext[CoroutineName]}")
    println("parentScope: Dispatcher [${Thread.currentThread().name}]")
    println("parentScope: CoroutineExceptionHandler ${parentScope.coroutineContext[CoroutineExceptionHandler]}\n")

    // Shares - CoroutineName, Dispatchers and ExceptionHandler from Parent
    val jobUsingDefaults = parentScope.launch {
        println("JobUsingDefaults: CoroutineName ${this.coroutineContext[CoroutineName]}")
        println("JobUsingDefaults: Dispatcher [${Thread.currentThread().name}]")
        println("JobUsingDefaults: CoroutineExceptionHandler ${this.coroutineContext[CoroutineExceptionHandler]}")
        println("JobUsingDefaults: Running in ${this.coroutineContext[Job]}\n")
    }
    // Only to enforce above logs are printed togather
    jobUsingDefaults.join()

    val coroutineContextExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(coroutineContext)
        println(throwable.message)
    }
    // Overrides - CoroutineName, Dispatchers and ExceptionHandler from Parent
    val jobWithCustomParams = parentScope.launch(CoroutineName("New Sample CN") + coroutineContextExceptionHandler) {
        println("JobWithCustomParams: CoroutineName ${this.coroutineContext[CoroutineName]}")
        println("JobWithCustomParams: Dispatcher [${Thread.currentThread().name}]")
        println("JobWithCustomParams: CoroutineExceptionHandler ${this.coroutineContext[CoroutineExceptionHandler]}")
        println("JobWithCustomParams: Running in ${this.coroutineContext[Job]}")
    }

}
