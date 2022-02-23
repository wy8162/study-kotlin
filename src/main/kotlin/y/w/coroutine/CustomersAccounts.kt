package y.w.coroutine

import kotlinx.coroutines.*

// Models
data class CustomerAccount(val customerId: String, val accountNumber: String, val routingNumber: String)
data class Request(val apiKey: String, val customerId: String)
data class ResResult(val httpStatus: Int, val error: String?, val customerAccounts: List<CustomerAccount>?)

fun main() = runBlocking {

}

suspend fun <T> serviceFunc(t: T, f: (T) -> List<CustomerAccount>): ResResult = coroutineScope {
    val value = async {
        f(t)
    }

    try {
        ResResult(
            httpStatus = 200,
            error = null,
            customerAccounts = value.await()
        )
    } catch (e: Exception) {
        ResResult(500, e.message, null)
    }
}

suspend fun serviceableAccounts(request: Request): List<CustomerAccount> = coroutineScope {
    val allAccounts = async(Dispatchers.IO) {

        listOf<CustomerAccount>()
    }

    val serviceableAccounts = async(Dispatchers.IO) {

        listOf<CustomerAccount>()
    }

    allAccounts.await() + serviceableAccounts.await()
}