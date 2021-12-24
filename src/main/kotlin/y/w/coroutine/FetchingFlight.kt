package y.w.coroutine

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.*

private const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
private const val FLIGHT_ENDPOINT = "$BASE_URL/flight"
private const val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"

fun fetchingFlightApp() {
    println("Started...${Thread.currentThread().name}")
    runBlocking { // Coroutine builder which blocks until the coroutine finishes
        launch {
            val flight = fetchFlight("Yang Wang")
            println("$flight .. ${Thread.currentThread().name}")
        }
    }
    println("Finished")
}

// coroutineScope grants access to async.
// If the coroutine started using async() throws an
// exception, then that exception will be propagated
// to the caller through the call to await().
suspend fun fetchFlight(passengerName: String): FlightStatus = coroutineScope {
    val client = HttpClient(CIO)
    val flightResponse: Deferred<String> = async {
        println("Start fetchFlight - ${Thread.currentThread().name}")
        client.get<String>(FLIGHT_ENDPOINT).also {
            println("End flight info - ${Thread.currentThread().name}")
        }
    }

    // "async" - await() will block the flow of execution but not the thread of execution
    val loyaltyResponse: Deferred<String> = async {
        println("Start loyalty - ${Thread.currentThread().name}")
        client.get<String>(LOYALTY_ENDPOINT).also {
            println("End loyalty info - ${Thread.currentThread().name}")
        }
    }

    FlightStatus.parse(
        passengerName = passengerName,
        flightResponse = flightResponse.await(),
        loyaltyResponse = loyaltyResponse.await()
    )
}
